var selectedRow = null

function onFormSubmit() {
    if (validate()) {
        var formData = readFormData();
        if (selectedRow == null)
            insertNewRecord(formData);
        else
            updateRecord(formData);
        resetForm();
    }
}

function readFormData() {
    var formData = {};
	
	formData["id"] =  document.getElementById("id").value;
    formData["dependente"] = document.getElementById("dependente").value;
    formData["grau"] = document.getElementById("grau").value;
    //formData["titular"] = document.getElementById("titular").value;
    return formData;
}

function insertNewRecord(data) {
    var table = document.getElementById("dependentesTable").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.id;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data.dependente;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data.grau;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = `<a onClick="onEdit(this)">Edit</a>
                       <a onClick="onDelete(this)">Delete</a>`;
}

function resetForm() {
	document.getElementById("id").value = "";
    document.getElementById("dependente").value = "";
    document.getElementById("grau").value = "";
    selectedRow = null;
}

function onEdit(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("id").value = selectedRow.cells[0].innerHTML;
    document.getElementById("dependente").value = selectedRow.cells[1].innerHTML;
    document.getElementById("grau").value = selectedRow.cells[2].innerHTML;
	//document.getElementById("titular").value = selectedRow.cells[3].innerHTML;
}

function updateRecord(formData) {
    selectedRow.cells[0].innerHTML = formData.id;
    selectedRow.cells[1].innerHTML = formData.dependente;
    selectedRow.cells[2].innerHTML = formData.grau;
    //selectedRow.cells[3].innerHTML = formData.city;
}

function onDelete(td) {
    if (confirm('Tem certeza que quer deletar?')) {
        row = td.parentElement.parentElement;
        document.getElementById("dependentesTable").deleteRow(row.rowIndex);
        resetForm();
    }
}

function validate() {
    isValid = true;
    if (document.getElementById("dependente").value == "") {
        isValid = false;
        document.getElementById("fullNameValidationError").classList.remove("hide");
    } else {
        isValid = true;
        if (!document.getElementById("fullNameValidationError").classList.contains("hide"))
            document.getElementById("fullNameValidationError").classList.add("hide");
    }
    return isValid;
}