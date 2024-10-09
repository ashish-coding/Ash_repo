package com.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.dao.BookRepository;
import com.api.entity.Books;

@Service
public class ServiceImpl {
	
	@Autowired 
	BookRepository bookRepository;
	
//	private static List<Books> list = new ArrayList<>();
//	
//	static {
//		list.add(new Books(101, "Raja Shiv Chatrapati", "Babasaheb Purandare"));
//		list.add(new Books(155, "Chava", "Ranjeet Desai"));
//		list.add(new Books(198, "Radhey", "Kanitkar"));
//	}
	
	/*get all books*/
	public List<Books> getAllBooks() {
		return (List<Books>) bookRepository.findAll();
	}
	
	/*get single book by id*/
	public Books getBookById(int id) {
		Books singleEntity = null;
		try {
			singleEntity = bookRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return singleEntity; 
	}
	
	public Books addBook(Books e) {
		
		return bookRepository.save(e);
	}
	
	public void deleteBook(int id) {
//		list.stream().filter(t -> {
//			if(t.getId() != id) {
//				return true;
//			}else {
//				return false;
//			}
//		}).collect(Collectors.toList());
//		list = list.stream().filter(t -> t.getId() != id).collect(Collectors.toList());
		bookRepository.deleteById(id);
	}

	public void updateBook(Books e2,int id) {
//		list = list.stream().map(b ->{
//			if(b.getId() == id) {
//				b.setTitle(e2.getTitle());
//				b.setAuthor(e2.getAuthor());
//			}
//			return b;
//		}).collect(Collectors.toList());
		
		e2.setId(id);
		bookRepository.save(e2);
//		
	}
}
