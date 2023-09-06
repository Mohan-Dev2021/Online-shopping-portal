package com.online.shop.dto;

<<<<<<< HEAD
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
=======
>>>>>>> 93392206682172df59cd318300eed817357448da
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author Sneka S
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageDto {
<<<<<<< HEAD
	
=======
>>>>>>> 93392206682172df59cd318300eed817357448da
	private String id;

	private String imageId;

	private String imageName;
<<<<<<< HEAD
	
    @NotNull
	@Valid
	private String imageFormat;
	
	private byte[] image;
=======

	private String imageFormat;
>>>>>>> 93392206682172df59cd318300eed817357448da
}
