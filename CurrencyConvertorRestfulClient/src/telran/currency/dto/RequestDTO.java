package telran.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {
	public String currencyFrom;
	public String currencyTo;
	public Double amount;
}
