package cool.mawei.constant.funci;

import cool.mawei.dto.BookDTO;
import cool.mawei.dto.BookResponse;

import java.util.function.Function;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-04 22:28
 */
public class FIBookDTOToBookResponse implements Function<BookDTO, BookResponse> {

    /**
     * Applies this function to the given argument.
     *
     * @param bookDTO the function argument
     * @return the function result
     */
    @Override
    public BookResponse apply(BookDTO bookDTO) {
        return BookResponse.newBuilder()
                .setId(bookDTO.getId())
                .setName(bookDTO.getName())
                .setAuthor(bookDTO.getAuthor())
                .setSize(bookDTO.getSize())
                .setPages(bookDTO.getPages())
                .setDate(bookDTO.getDate()).build();
    }
}
