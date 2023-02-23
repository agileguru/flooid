package com.doit.flooid.rest.controller;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.Callable;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.doit.flooid.rest.data.ResponseData;
import com.google.common.base.Stopwatch;

@RestController
public class DefaultController {
	private static final int INDUCED_DELAY = 5;
	
	@GetMapping({"","/"})
	public Callable<ResponseEntity<ResponseData>> home() {
		ResponseData data = new ResponseData();
		data.setAppName(DefaultController.class.getSimpleName());
		
		return () -> {
			return new ResponseEntity<ResponseData>(data,HttpStatus.OK);
		};
	}
	@GetMapping("/delay")
	public Callable<ResponseEntity<ResponseData>> delayResponse() {
		ResponseData data = new ResponseData();
		data.setAppName(DefaultController.class.getSimpleName() + " Took around " + INDUCED_DELAY + " Seconds");
		return () -> {
			doSomeLongTimeProcessing();
			return new ResponseEntity<ResponseData>(data,HttpStatus.OK);
		};
	}

	private void doSomeLongTimeProcessing() {
        Stopwatch watch = Stopwatch.createStarted();

        // delay for 7 seconds
        while (watch.elapsed(SECONDS) < INDUCED_DELAY ) {
          int i = Integer.MIN_VALUE;
          while (i < Integer.MAX_VALUE) {
              i++;
          }
        }
    }
	
	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(value = HttpStatus.PAYLOAD_TOO_LARGE)
	public ResponseData handleException(Exception ex) {
		ResponseData data = new ResponseData();
		data.setAppName("The Payload could not be processed");
		return data ;
	}
}
