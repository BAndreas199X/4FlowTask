package com.example.andreas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.andreas.model.Container;
import com.example.andreas.model.Good;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GoodsController {
	
	@Autowired
	private Container containerInstance;

	@PutMapping("goods/load")
	ResponseEntity<String> loadGoodIntoContainer(Good newGood){
		
		String result = containerInstance.getInstance().loadGood(newGood);
		
		if(result.equals("Good with the data "+newGood+" loaded successfully!")) {
			return new ResponseEntity<>(result,HttpStatus.OK);
		}else {
			return new ResponseEntity<>(result,HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("goods/unload")
	ResponseEntity<Object> unloadGoodFromContainer(){
		
		if(containerInstance.getInstance().isContainerEmpty()) {
			return new ResponseEntity<>("No good could be removed, because the "
					+ "container is empty!",HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(containerInstance.getInstance().unloadGood(),
				HttpStatus.OK);
	}
	
	@GetMapping("goods/totalweight")
	ResponseEntity<Double> getContainerGoodsWeight(){
		return new ResponseEntity<>(containerInstance.getInstance().getTotalWeight(),
				HttpStatus.OK);
	}
	
	@GetMapping("goods/maxprize")
	ResponseEntity<Object> getContainersMaxPrize(){
		
		if(containerInstance.getInstance().isContainerEmpty()) {
			return new ResponseEntity<>("The container is empty! The highest prize "
					+ "could not be determined.",HttpStatus.OK);
		}
		
		return new ResponseEntity<>(containerInstance.getInstance().getMaxPrize(),
				HttpStatus.OK);
		
	}
	
	@GetMapping("goods")
	ResponseEntity<List<Good>> getAllGoods(){
		return new ResponseEntity<>(containerInstance.getInstance()
				.getAllGoodsInContainer(),HttpStatus.OK);
	}
	
	@GetMapping("goods/limit")
	ResponseEntity<Double> getWeightLimit(){
		return new ResponseEntity<>(containerInstance.getInstance().getWeightLimit(),
				HttpStatus.OK);
	}
	
	@ExceptionHandler({Exception.class})
	public ResponseEntity<String> generalException(HttpServletRequest req,Exception e) {
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
				"Unknown Error in context-path '"+req.getRequestURI()+
				"'. Error message: "+e.toString());
	}
}
