package pojos.resources.response;

import lombok.Data;

@Data
public class CreateFolder {
	private String method;
	private Boolean templated;
	private String href;
}