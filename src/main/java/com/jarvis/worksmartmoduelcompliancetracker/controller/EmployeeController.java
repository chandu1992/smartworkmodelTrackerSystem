package com.jarvis.worksmartmoduelcompliancetracker.controller;

import com.jarvis.worksmartmoduelcompliancetracker.constant.ApplicationConstant;
import com.jarvis.worksmartmoduelcompliancetracker.dto.ExceptionOdDataDto;
import com.jarvis.worksmartmoduelcompliancetracker.entity.ExceptionOdDataEntity;
import com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler.exceptions.InternalServerError;
import com.jarvis.worksmartmoduelcompliancetracker.exceptionhandler.exceptions.UnauthorizedException;
import com.jarvis.worksmartmoduelcompliancetracker.responsehandler.ResponseHandler;
import com.jarvis.worksmartmoduelcompliancetracker.service.ExceptionalOdRaiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class EmployeeController {

    @Value("${mytoken}")
    String validateTokenId;
    @Autowired
    private ExceptionalOdRaiseService exceptionalOdataService;

    @CrossOrigin
    @PostMapping("/raise")
    public ResponseEntity<ResponseHandler<String>> getSubConData(
            @RequestHeader String username,
            @RequestHeader String empId,
            @RequestHeader String tokenId,
            @RequestHeader String jwtTokenId,
            @RequestBody ExceptionOdDataDto odData) {

        try {

            // Validate token
            if (!tokenId.equals(validateTokenId)) {
                throw new UnauthorizedException(ApplicationConstant.UNAUTHORIZED);
            }

            // Call service
            ResponseHandler<String> response =
                    new ResponseHandler<>(
                            exceptionalOdataService.raiseExceptionOdata(
                                    jwtTokenId,
                                    username,
                                    empId,
                                    odData),
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

    @CrossOrigin
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseHandler<String>> deleteOdByUser(@RequestHeader String tokenId, @RequestHeader long request) {

        try {
            if (!tokenId.equals(validateTokenId)) {
                throw new UnauthorizedException(ApplicationConstant.UNAUTHORIZED);
            }

            ResponseHandler<String> response = new ResponseHandler<>(exceptionalOdataService.deleteOdByUser(request), 200, ApplicationConstant.SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            throw new UnauthorizedException(e.getMessage());
        } catch (Exception ex) {
            throw new InternalServerError(ex.getMessage());
        }
    }

    @CrossOrigin
    @DeleteMapping("/records")
    public ResponseEntity<ResponseHandler<List<ExceptionOdDataEntity>>> getPendingOdRaiseByUser(@RequestHeader String tokenId, @RequestHeader String empId) {

        try {
            if (!tokenId.equals(validateTokenId)) {
                throw new UnauthorizedException(ApplicationConstant.UNAUTHORIZED);
            }

            ResponseHandler<List<ExceptionOdDataEntity>> response = new ResponseHandler<>(exceptionalOdataService.getPendingReqForEmp(empId), 200, ApplicationConstant.SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (UnauthorizedException e) {
            throw new UnauthorizedException(e.getMessage());
        } catch (Exception ex) {
            throw new InternalServerError(ex.getMessage());
        }
    }
}
