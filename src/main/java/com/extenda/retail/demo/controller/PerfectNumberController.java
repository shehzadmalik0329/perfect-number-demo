package com.extenda.retail.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.extenda.retail.demo.common.ApplicationConstants;

@RestController
@RequestMapping("/rest/api")
public class PerfectNumberController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/checkPerfectNumber/{number}")
	public ResponseEntity<String> checkPerfectNumber(@PathVariable String number){
		
		log.info("Check perfect number for {}",number);
		
			Integer inputNumber = Integer.parseInt(number);
			boolean flag = isPerfect(inputNumber);
			String message = flag ? ApplicationConstants.PERFECT_NUMBER_MESSAGE : ApplicationConstants.NOT_A_PERFECT_NUMBER_MESSAGE;
			return ResponseEntity.ok().body(message);
		
	}
	@GetMapping("/getAllPerfectNumbers")
	public ResponseEntity<List<Integer>> getPerfectNumbers(@RequestParam String start, @RequestParam String end){
		
		log.info("Check perfect numbers between {} and {}",start, end);
		
		List<Integer> perfectNumbers = new ArrayList<Integer>();
		
		if(!ObjectUtils.isEmpty(start) && !ObjectUtils.isEmpty(end)){
			Integer startNumber = Integer.parseInt(start);
			Integer endNumber = Integer.parseInt(end);
			for(int i=startNumber; i<=endNumber; i++){
				if(isPerfect(i)){
					perfectNumbers.add(i);
				}
			}
		}
		return ResponseEntity.ok().body(perfectNumbers);
	}
	
	private boolean isPerfect(int inputNumber)
	{
	    int sum = 1;
	    for (int num = 2; num * num <= inputNumber; num++)
	    {
	        if (inputNumber % num==0)
	        {
	            if(num * num != inputNumber)
	                sum = sum + num + inputNumber / num;
	            else
	                sum = sum + num;
	        }
	    }
	    if (sum == inputNumber && inputNumber != 1)
	        return true;
	 
	    return false;
	}

}
