package com.jarvis.worksmartmoduelcompliancetracker.controller;

import java.util.List;

import com.jarvis.worksmartmoduelcompliancetracker.constant.ApplicationConstant;
import com.jarvis.worksmartmoduelcompliancetracker.entity.ExceptionOdDataEntity;
import com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler.exceptions.InternalServerError;
import com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler.exceptions.UnauthorizedException;
import com.jarvis.worksmartmoduelcompliancetracker.responsehandler.ResponseHandler;
import com.jarvis.worksmartmoduelcompliancetracker.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    String validateTokenId;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @CrossOrigin
    @GetMapping("/records")
    public ResponseEntity<ResponseHandler<List<ExceptionOdDataEntity>>> getSubConData(
            @RequestHeader String tokenId) {

        try {

            // Validate token
            if (!tokenId.equals(validateTokenId)) {
                throw new UnauthorizedException(ApplicationConstant.UNAUTHORIZED);
            }

            // Fetch data from service
            ResponseHandler<List<ExceptionOdDataEntity>> response =
                    new ResponseHandler<>(
                            adminService.getRaiseExceptionDataForAdmin(),
                            HttpStatus.OK.value(),
                            ApplicationConstant.SUCCESS
                    );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (UnauthorizedException e) {

            throw new UnauthorizedException(e.getMessage());

        } catch (Exception ex) {

            throw new InternalServerError(ex.getMessage());

        }

    }

}