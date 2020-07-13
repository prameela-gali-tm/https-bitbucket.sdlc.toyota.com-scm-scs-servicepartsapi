package com.toyota.scs.serviceparts.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.toyota.scs.serviceparts.entity.VendorEntity;
import com.toyota.scs.serviceparts.model.PartDetailsModel;
import com.toyota.scs.serviceparts.repository.VendorRepositroy;
import com.toyota.scs.serviceparts.serviceImpl.PartDetailsServiceImpl;

@RestController
@RequestMapping("/api")
public class VendorController {

	@Autowired
	private VendorRepositroy vendorRepositroy;
	
	@Autowired
	private PartDetailsServiceImpl partdetailsService;
	
	@PostMapping("/vendors")
	public VendorEntity createVendor(@RequestBody VendorEntity vendor) {
		return vendorRepositroy.save(vendor);
	}
	
	@GetMapping("/partdetails")
	public ResponseEntity<List<PartDetailsModel>> getPartDetails(
			@RequestParam(name="partNumber", required = true) String partNumber,
			@RequestParam(name="vendorCode", required = true) String vendorCode){
		
		List<PartDetailsModel> partDetails = new ArrayList<PartDetailsModel>();	
		partDetails = partdetailsService.findPartDetails(partNumber, vendorCode);
		return new ResponseEntity<>(partDetails,HttpStatus.OK);
		
	}
	
}
