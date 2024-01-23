package com.dws.challenge.domain;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FundTransfer {
	
	@NotNull
	@NotEmpty
	private String fromAccount;
	@NotNull
	@NotEmpty
	private String toAccount;
	@NotNull
	@Min(value = 0, message = "Requested amount must be positive.")
	private BigDecimal amount;

}
