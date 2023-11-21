package sg.khomeini.carpark.integration.sg.gov.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wgs84Response {
    private Double latitude;
    private Double longitude;
}
