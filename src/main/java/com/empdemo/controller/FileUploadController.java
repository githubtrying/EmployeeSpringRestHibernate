package com.empdemo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.empdemo.model.EmpResponse;

@RestController
@PropertySource(value = { "classpath:application.properties" })
public class FileUploadController {
	@Autowired
	private Environment environment;

	@RequestMapping(value = "/upload")
	public EmpResponse upload(@RequestParam("file") MultipartFile file,
			@RequestParam("username") String name) throws IOException {
		EmpResponse empResponse = new EmpResponse();
		String location = environment
				.getRequiredProperty("user.profileStoreLocation");
		byte[] bytes;

		if (!file.isEmpty()) {
			try {
				bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(location
								+ file.getOriginalFilename())));
				stream.write(bytes);
				stream.close();
				empResponse.setMessage("You successfully uploaded " + name
						+ "!");
			} catch (Exception e) {
				empResponse.setMessage("You failed to upload " + name + " => "
						+ e.getMessage());
			}
		} else {
			empResponse.setMessage("You failed to upload " + name
					+ " because the file was empty.");
		}
		return empResponse;
	}
}
