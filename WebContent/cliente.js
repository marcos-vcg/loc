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
	
	formData["idDep"] =  document.getElementById("idDep").value;
    formData["nomeDep"] = document.getElementById("nomeDep").value;
    formData["grauDep"] = document.getElementById("grauDep").value;
    //formData["titular"] = document.getElementById("titular").value;
    return formData;
}

function insertNewRecord(data) {
    var table = document.getElementById("dependentesTable").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.idDep;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data.nomeDep;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data.grauDep;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = `<a onClick="onEdit(this)">Edit</a>
                       <a onClick="onDelete(this)">Delete</a>`;
}

function resetForm() {
	document.getElementById("idDep").value = "";
    document.getElementById("nomeDep").value = "";
    document.getElementById("grauDep").value = "";
    selectedRow = null;
}

function onEdit(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("idDep").value = selectedRow.cells[0].innerHTML;
    document.getElementById("nomeDep").value = selectedRow.cells[1].innerHTML;
    document.getElementById("grauDep").value = selectedRow.cells[2].innerHTML;
	//document.getElementById("titular").value = selectedRow.cells[3].innerHTML;
}

function updateRecord(formData) {
    selectedRow.cells[0].innerHTML = formData.idDep;
    selectedRow.cells[1].innerHTML = formData.nomeDep;
    selectedRow.cells[2].innerHTML = formData.grauDep;
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
    if (document.getElementById("nomeDep").value == "") {
        isValid = false;
        document.getElementById("fullNameValidationError").classList.remove("hide");
    } else {
        isValid = true;
        if (!document.getElementById("fullNameValidationError").classList.contains("hide"))
            document.getElementById("fullNameValidationError").classList.add("hide");
    }
    return isValid;
}