// ActionScript file

import mx.collections.ArrayCollection;
import mx.controls.Alert;
import mx.rpc.events.ResultEvent;

import com.opentravelsoft.employee.mgmt.Employee;
import com.opentravelsoft.employee.mgmt.flex.FlexServiceRO;

[Bindable]
public var employees : ArrayCollection = new ArrayCollection();

public function queryAll() : void {
    FlexServiceRO.INSTANCE.queryAll(
        function(result : ResultEvent) : void {
            employees = new ArrayCollection();
            for each (var o : Employee in result.result as Array) {
                employees.addItem(o);
            }
        }
    );
}

public function queryByName() : void {
	var name : String = nameText.text;
	if (name.length==0) {
	   queryAll();
	}
	else {		
		FlexServiceRO.INSTANCE.queryByName(name,
		    function(result : ResultEvent) : void {
                employees = new ArrayCollection();
                for each (var o : Employee in result.result as Array) {
                    employees.addItem(o);
                }
            }
        );
	}
}

public function preCreateEmployee() : void {
    form_name.text = "";
    form_title.text = "";
    form_gender_male.selected = false;
    form_gender_female.selected = false;
    form_birth.text = "";
    form_panel.visible = true;
}

public function createEmployee() : void {
    FlexServiceRO.INSTANCE.createEmployee(
        form_name.text,
        form_title.text,
        form_gender_male.selected,
        form_birth.selectedDate,
        function(result : ResultEvent) : void {
        	employees.addItem(result.result as Employee);
        }
    );
    form_panel.visible = false;
}

public function deleteEmployee() : void {
    var item : Employee = employeeDataGrid.selectedItem as Employee;
	if (item==null) {
		Alert.show("Please select an employee first!", "Error");
	}
	else {
		FlexServiceRO.INSTANCE.deleteEmployee(item.userId,
		    function(result : ResultEvent) : void {
		    	employees.removeItemAt(employeeDataGrid.selectedIndex);
		    }
		);
	}
}

