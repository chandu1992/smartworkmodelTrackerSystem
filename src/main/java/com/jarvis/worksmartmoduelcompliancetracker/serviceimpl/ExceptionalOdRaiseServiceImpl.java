package com.jarvis.worksmartmoduelcompliancetracker.serviceimpl;

import com.jarvis.worksmartmoduelcompliancetracker.constant.ApplicationConstant;
import com.jarvis.worksmartmoduelcompliancetracker.constant.ApprovalLevel;
import com.jarvis.worksmartmoduelcompliancetracker.dto.ExceptionOdDataDto;
import com.jarvis.worksmartmoduelcompliancetracker.entity.ExceptionOdDataEntity;
import com.jarvis.worksmartmoduelcompliancetracker.repository.ExceptionOdDataRepo;
import com.jarvis.worksmartmoduelcompliancetracker.service.ExceptionalOdRaiseService;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

public class ExceptionalOdRaiseServiceImpl implements ExceptionalOdRaiseService {

    @Value("${managerDetails.url}")
    private String url;

    @Autowired
    private ExceptionOdDataRepo exceptionalOdDataRepo;

    @Override
    public String raiseExceptionOdata(String jwtTokenId, String username, String empId, ExceptionOdDataDto odData) {

        ExceptionOdDataEntity od = new ExceptionOdDataEntity();

        List<ExceptionOdDataEntity> raiseOrNot =
                exceptionalOdDataRepo.findOdAlreadyRaiseOrNot(
                        odData.getStartDate(),
                        odData.getEndDate(),
                        empId
                );

        if (raiseOrNot.isEmpty()) {

            od.setEmpName(odData.getEmpName());
            od.setEmpId(odData.getEmpId());
            od.setEmailId(odData.getEmailId());
            od.setUsername(odData.getUsername());
            od.setStartDate(odData.getStartDate());
            od.setEndDate(odData.getEndDate());

            od.setWeekNo(odData.getWeekNo());
            od.setReasonDetails(odData.getReasonDetails());

            ApprovalLevel level = ApprovalLevel.fromWeek(odData.getWeekNo());
            System.out.println(level);

            String label = level.getLabel();
            od.setManagerApprovalLevel(label);

            od.setManagerName(odData.getManagerName());
            od.setManagerEmpId(odData.getManagerEmpId());
            od.setManagerEmailId(odData.getManagerEmailId());

            JSONObject data = getManagerDetailsFromWorld(jwtTokenId, username, empId);

            switch (label) {
                case "L1":
                    JSONObject l1 = data.getJSONObject("L1");

                    od.setApproverManagerName(
                            l1.getString(ApplicationConstant.FULL_NAME));
                    od.setApproverManagerEmpId(
                            l1.getString(ApplicationConstant.EMP_ID));
                    od.setApproverManagerUserName(
                            l1.getString(ApplicationConstant.USER_NAME));
                    od.setApproverManagerEmailId(
                            l1.getString(ApplicationConstant.OFFICIAL_EMAIL));
                    break;

                case "L2":
                    JSONObject l2 = data.getJSONObject("L2");

                    od.setApproverManagerName(
                            l2.getString(ApplicationConstant.FULL_NAME));
                    od.setApproverManagerEmpId(
                            l2.getString(ApplicationConstant.EMP_ID));
                    od.setApproverManagerUserName(
                            l2.getString(ApplicationConstant.USER_NAME));
                    od.setApproverManagerEmailId(
                            l2.getString(ApplicationConstant.OFFICIAL_EMAIL));
                    break;

                case "L3":
                    JSONObject l3 = data.getJSONObject("L3");

                    od.setApproverManagerName(
                            l3.getString(ApplicationConstant.FULL_NAME));
                    od.setApproverManagerEmpId(
                            l3.getString(ApplicationConstant.EMP_ID));
                    od.setApproverManagerUserName(
                            l3.getString(ApplicationConstant.USER_NAME));
                    od.setApproverManagerEmailId(
                            l3.getString(ApplicationConstant.OFFICIAL_EMAIL));
                    break;

                default:
                    JSONObject l1Def = data.getJSONObject("L1");

                    od.setApproverManagerName(
                            l1Def.getString(ApplicationConstant.FULL_NAME));
                    od.setApproverManagerEmpId(
                            l1Def.getString(ApplicationConstant.EMP_ID));
                    od.setApproverManagerUserName(
                            l1Def.getString(ApplicationConstant.USER_NAME));
                    od.setApproverManagerEmailId(
                            l1Def.getString(ApplicationConstant.OFFICIAL_EMAIL));
            }

            od.setOdStatus(0);
            od.setStatusText("Pending");
            od.setReason(odData.getReason());
            od.setCompliantStatus("Non-Compliant");
            exceptionalOdDataRepo.save(od);


        } else {
            return "An exceptional OD has already been raised...";
        }

        return "Od raise successfully";
    }

    @Override
    @Transactional
    public String deleteOdByUser(long request) {
        Optional<ExceptionOdDataEntity> odData =
                exceptionalOdDataRepo.findByRequestId(request);

        if (odData.isPresent()) {

            odData.get().setStatusText("Deleted By User");
            odData.get().setOdStatus(-1);

            exceptionalOdDataRepo.save(odData.get());

            return "od deleted";
        }

        return "The record of give req id not present so not able to delete";

    }

    @Override
    public List<ExceptionOdDataEntity> getPendingReqForEmp(String empId) {
        return exceptionalOdDataRepo.findPendingRaisesByUser(empId);
    }

    public JSONObject getManagerDetailsFromWorld(String tokenId,
                                                           String username,
                                                           String empId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("appKey", "headerValue_ES");
        headers.add("empId", empId);
        headers.add("loginUsername", username);
        headers.add("username", username);
        headers.add("tokenId", tokenId);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        JSONObject obj = new JSONObject(response.getBody());
        return (JSONObject) obj.get("data");
    }
}
