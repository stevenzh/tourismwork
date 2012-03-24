// This ActionScript class is generated automatically 
// by ANT task. Please DO NOT modify it.

package com.opentravelsoft.employee.mgmt.flex {

    import mx.rpc.AbstractOperation;
    import mx.rpc.events.FaultEvent;
    import mx.rpc.events.ResultEvent;
    import mx.rpc.remoting.mxml.RemoteObject;



    public class FlexServiceRO {

        private var ro : RemoteObject;

        public static const INSTANCE : FlexServiceRO = new FlexServiceRO();

        public function FlexServiceRO() {
            ro = new RemoteObject();
            ro.destination = "flexService";
            ro.concurrency = "last";
            ro.makeObjectsBindable = true;
            ro.showBusyCursor = false;
        }

        // auto-generated method stub:
        public function createEmployee(userId : String, userName : String, sex : Boolean, birthday : Date, result : Function = null, fault : Function = null) : void {
            var op : AbstractOperation = ro.getOperation("createEmployee");
            if (result!=null) {
                op.addEventListener(ResultEvent.RESULT, result);
            }
            if (fault!=null) {
                op.addEventListener(FaultEvent.FAULT, fault);
            }
            var f : Function = function() : void {
                op.removeEventListener(ResultEvent.RESULT, f);
                op.removeEventListener(FaultEvent.FAULT, f);
                if (result!=null) {
                    op.removeEventListener(ResultEvent.RESULT, result);
                }
                if (fault!=null) {
                    op.addEventListener(FaultEvent.FAULT, fault);
                }
            }
            op.addEventListener(ResultEvent.RESULT, f);
            op.addEventListener(FaultEvent.FAULT, f);
            op.send(userId, userName, sex, birthday);
        }

        // auto-generated method stub:
        public function deleteEmployee(userId : int, result : Function = null, fault : Function = null) : void {
            var op : AbstractOperation = ro.getOperation("deleteEmployee");
            if (result!=null) {
                op.addEventListener(ResultEvent.RESULT, result);
            }
            if (fault!=null) {
                op.addEventListener(FaultEvent.FAULT, fault);
            }
            var f : Function = function() : void {
                op.removeEventListener(ResultEvent.RESULT, f);
                op.removeEventListener(FaultEvent.FAULT, f);
                if (result!=null) {
                    op.removeEventListener(ResultEvent.RESULT, result);
                }
                if (fault!=null) {
                    op.addEventListener(FaultEvent.FAULT, fault);
                }
            }
            op.addEventListener(ResultEvent.RESULT, f);
            op.addEventListener(FaultEvent.FAULT, f);
            op.send(userId);
        }

        // auto-generated method stub:
        public function queryByName(userName : String, result : Function = null, fault : Function = null) : void {
            var op : AbstractOperation = ro.getOperation("queryByName");
            if (result!=null) {
                op.addEventListener(ResultEvent.RESULT, result);
            }
            if (fault!=null) {
                op.addEventListener(FaultEvent.FAULT, fault);
            }
            var f : Function = function() : void {
                op.removeEventListener(ResultEvent.RESULT, f);
                op.removeEventListener(FaultEvent.FAULT, f);
                if (result!=null) {
                    op.removeEventListener(ResultEvent.RESULT, result);
                }
                if (fault!=null) {
                    op.addEventListener(FaultEvent.FAULT, fault);
                }
            }
            op.addEventListener(ResultEvent.RESULT, f);
            op.addEventListener(FaultEvent.FAULT, f);
            op.send(userName);
        }

        // auto-generated method stub:
        public function queryAll(result : Function = null, fault : Function = null) : void {
            var op : AbstractOperation = ro.getOperation("queryAll");
            if (result!=null) {
                op.addEventListener(ResultEvent.RESULT, result);
            }
            if (fault!=null) {
                op.addEventListener(FaultEvent.FAULT, fault);
            }
            var f : Function = function() : void {
                op.removeEventListener(ResultEvent.RESULT, f);
                op.removeEventListener(FaultEvent.FAULT, f);
                if (result!=null) {
                    op.removeEventListener(ResultEvent.RESULT, result);
                }
                if (fault!=null) {
                    op.addEventListener(FaultEvent.FAULT, fault);
                }
            }
            op.addEventListener(ResultEvent.RESULT, f);
            op.addEventListener(FaultEvent.FAULT, f);
            op.send();
        }


    }
}
