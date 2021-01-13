package com.qweed.qweed;

//import com.qweed.qweed.interfaces.IMedicineRepository;
import com.qweed.qweed.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QweedApplication {
	//@Autowired
	//IMedicineRepository medicineRepository;
	@Autowired
	IUserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(QweedApplication.class, args);
	}

}


