/**
 * Created by gaviriajesus on 30/8/18.
 */
({
    runJob: function(component, event, helper) {
        var action = component.get('c.runJobApex');

        action.setParams({
              "platformJobId": 'a1Y46000001NPs8EAG',
        });
        action.setCallback(this, function(response) {
            if (component.isValid() && response.getState() === 'SUCCESS') {
                var btn = event.getSource();
                btn.set("v.disabled", true); //Disable the button
                component.set("v.showNotification", true); //Show the Notification Panel
                component.set('v.copadoJobId', response.getReturnValue());

            } else
                console.error(response);
        });
        $A.enqueueAction(action);
    },
    newNotification: function(component, evt) {
        console.log('EVENT RECEIVED BY PARENT COMPONENT_ newNotification evt:', evt);
        var noti = evt.getParam("finishNotification");
        var homeEvt = $A.get("e.force:navigateToSObject");
        homeEvt.setParams({
            "recordId": component.get("v.recordId"),
            "slideDevName": "detail",
            "isredirect" : true
        });
        homeEvt.fire();

    }
})