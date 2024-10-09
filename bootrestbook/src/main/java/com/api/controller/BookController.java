package com.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.entity.Books;
import com.api.helper.FileuploadHelper;
import com.api.serviceimpl.ServiceImpl;


@RestController
public class BookController {
	
	
	
	@Autowired
	ServiceImpl service;
	
	@GetMapping("/books")
	public ResponseEntity<List<Books>> getBooks() {
		
		List<Books> list = service.getAllBooks();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Books> getBookById(@PathVariable("id") int id) {
		Books book =  service.getBookById(id);
		if(book==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/addBook")
	public ResponseEntity<Books> addBook(@RequestBody Books e) {
		Books b = null;
		try {
			b = service.addBook(e);
			System.out.println(b);
			return ResponseEntity.status(HttpStatus.CREATED).body(b);
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable("id") int id) {
		try {
			service.deleteBook(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Books> updateBook(@RequestBody Books e2,@PathVariable("id") int id) {
		 try {
			 this.service.updateBook(e2,id);
			 return ResponseEntity.ok().body(e2);
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		 
	}
}
