package it.contrader.inbook.service;

import it.contrader.inbook.converter.LibraryConverter;
import it.contrader.inbook.dto.*;
import it.contrader.inbook.exception.NotExistException;
import it.contrader.inbook.model.Library;
import it.contrader.inbook.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
public class LibraryService extends AbstractService<Library, LibraryDTO>{

    @Autowired
    LibraryConverter libraryConverter;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    BookService bookService;

    @Autowired
    BuyService buyService;

    @Autowired
    private PasswordEncoder encoder;


    public List<LibraryDTO> getByAdmin(long adminId){
        return libraryConverter.toListDTO(libraryRepository.findByAdmins_Id(adminId));
    }

    public List<LibraryDTO> getByAdmin(String adminEmail){
        return libraryConverter.toListDTO(libraryRepository.findByAdmins_Email(adminEmail));
    }

    @Override
    public void delete(Long id){
        try {
//        LibraryDTO lTd = converter.toDTO(repository.getById(id));
            List<BookDTO> bTd = bookService.getByLibrary(id);

            if (!bTd.isEmpty())
                for (BookDTO b : bTd) {
                    b.setLibrary(null);
                    bookService.save(b);
                }

            libraryRepository.deleteById(id);
        }
        catch (Exception ex){
            throw  new NotExistException("Does not exist anymore");
        }

    }

    public List<LibraryDTO> getByAdminsNotNull(){
        return libraryConverter.toListDTO(libraryRepository.findByAdminsNotNull());
    }

    public List<LibraryDTO> getByCity(String city){
        return libraryConverter.toListDTO(libraryRepository.findByCity(city));
    }

    public List<BookDTO> getRelatedBook(Long bookId) {
        BookDTO focus = bookService.read(bookId);
        final int relPageSize = 25;

        List<BookDTO> booksByCategory = bookService.getByCategory(focus.getCategory());
        List<BookDTO> booksByLibraryId = bookService.getByLibrary(focus.getLibrary().getId());

        List<BookDTO> combinedResult = new ArrayList<>(booksByCategory);
        combinedResult.retainAll(booksByLibraryId);

        if (combinedResult.size() < relPageSize) {
            for (LibraryDTO lib : this.getByCity(this.read(focus.getLibrary().getId()).getCity())) {
                if (!lib.getId().equals(focus.getLibrary().getId())) { // Avoid querying the same library again
                    for (BookDTO book : bookService.getByLibrary(lib.getId())) {
                        if (book.getCategory().equals(focus.getCategory())) {
                            combinedResult.add(book);
                        }
                        if (combinedResult.size() >= relPageSize) { // Exit loop if we have enough books
                            break;
                        }
                    }
                }
                if (combinedResult.size() >= relPageSize) { // Exit loop if we have enough books
                    break;
                }
            }
        }

        return combinedResult;

    }

    public LibraryProtectedDTO readProtected(Long id){
        return libraryConverter.toProtected(libraryRepository.getById(id));
    }

    public LibraryDTO toDTO(LibraryProtectedDTO lpDTO){
        return LibraryDTO.builder()
                .id(lpDTO.getId())
                .name(lpDTO.getName())
                .address(lpDTO.getAddress())
                .city(lpDTO.getCity())
                .nation(lpDTO.getNation())
                .province(lpDTO.getProvince())
                .description(lpDTO.getDescription())
                .admins(lpDTO.getAdmins())
                .build();
    }

    public LibraryDTO save(LibraryProtectedDTO lpDTO){
        return libraryConverter.toDTO(libraryRepository.save(libraryConverter.toEntity(lpDTO)));
    }


    public List<Long> getSales(Long libID){
        List<Long> sales = new ArrayList<>();
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

        for (LocalDate date = LocalDate.now(); !date.isBefore(thirtyDaysAgo); date = date.minusDays(1)) {
            final LocalDate currentDate = date;
            Long dailySales = buyService.getAll().stream()
                    .filter(buyDTO -> buyDTO.getBook().getLibrary().getId().equals(libID))
                    .filter(buyDTO -> buyDTO.getDate().isEqual(currentDate))
                    .mapToLong(buyDTO -> buyDTO.getQuantity())
                    .sum();
            sales.add(dailySales);
        }

        return sales;
    }

}
