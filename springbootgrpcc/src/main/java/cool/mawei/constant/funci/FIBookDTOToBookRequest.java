package cool.mawei.constant.funci;

import cool.mawei.dto.BookDTO;
import cool.mawei.dto.BookRequest;

import java.util.function.Function;

/**
 * 类功能描述
 *
 * @author Ma_wei
 * @since 2020-02-05 10:46
 */
public class FIBookDTOToBookRequest implements Function<BookDTO, BookRequest> {

    /**
     * Applies this function to the given argument.
     *
     * @param bookDTO the function argument
     * @return the function result
     */
    @Override
    public BookRequest apply(BookDTO bookDTO) {
        if (bookDTO == null){
            return null;
        }
        BookRequest request = BookRequest.newBuilder()
                .setId(bookDTO.getId())
                .setName(bookDTO.getName())
                .setSize(bookDTO.getSize())
                .setPages(bookDTO.getPages()).build();
        return request;
    }
}
