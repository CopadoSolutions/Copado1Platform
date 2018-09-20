/**
 * Created by gaviriajesus on 30/8/18.
 */

public with sharing class C1P_Account_GenerateDocumentsController {


    @AuraEnabled
    public static String runJobApex(String platformJobId) {
        Map<String, String> paramBody = new Map<String, String>();
        for(copado1p__Platform_Job_Parameter__c pj : [SELECT Name, copado1p__Value__c from copado1p__Platform_Job_Parameter__c where copado1p__Platform_Job__c = :platformJobId]) {
            paramBody.put(pj.Name, pj.copado1p__Value__c);
        }
        String response = copado1p.GlobalAPI.startPlatformJob(platformJobId, paramBody);
        return response;
    }

}