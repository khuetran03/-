package ra.ojt.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
public class RaResponse {
    private String type;
    private Long id;
    private Map<String, Object> data;
}
