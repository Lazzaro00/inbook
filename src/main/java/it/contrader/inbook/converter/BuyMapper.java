package it.contrader.inbook.converter;


import it.contrader.inbook.dto.BookDTO;
import it.contrader.inbook.dto.BuyDTO;
import it.contrader.inbook.dto.LibraryDTO;
import it.contrader.inbook.dto.UserDTO;
import it.contrader.inbook.model.Book;
import it.contrader.inbook.model.Buy;
import it.contrader.inbook.model.Library;
import it.contrader.inbook.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuyMapper {

    Buy toBuy(BuyDTO dto);

    BuyDTO toBuyDTO(Buy buy);

    Book toBook(BookDTO dto);

    BookDTO toBookDTO(Book book);

    Library toLibrary(LibraryDTO dto);

    LibraryDTO toLibraryDTO(Library lib);

    User toUser(UserDTO dto);

    UserDTO map(User value);

}
