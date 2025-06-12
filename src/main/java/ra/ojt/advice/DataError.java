package ra.ojt.advice;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataError<T> {
    private int code;
    private T message;
}
