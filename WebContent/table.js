var pageno =1;
const fetchTableData =() => {
$.get('/H2HBABBA2593/Invoice',{page: pageno }, function(data, textStatus, jqXHR){
buildTable(data)
});
}
(
function() {
    fetchTableData();
}
)()
function next_page(){
    pageno += 1;
    const table = document.getElementById('ProductTable1');
    table.innerHTML = "";
    fetchTableData();
}
function previous_page(){
    if(pageno > 1){
      pageno -= 1;
      fetchTableData();
    }
}
function buildTable(data){
const table = document.getElementById('ProductTable1');
for(let i in data){
console.log(data[i])
let tableRowEle = `<tr>
    <td><img src = 'images/check_box_outline_blank-black-18dp.svg' id= 'chk' +${i} ></td>
    <td>${data[i].name_customer}</td>
    <td>${data[i].cust_number}</td>
    <td>${data[i].invoice_id}</td>
    <td>${data[i].total_open_amount}</td>
    <td>${data[i].due_in_date}</td>
    <td>${data[i].predicted_payment_date}</td>
    <td>${data[i].notes}</td>
    </tr>`;
    table.innerHTML += tableRowEle;
}
}
var tick = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
function ticked(id){
	var a = id[id.length - 1];
	var b = id[id.length - 2];
	var x = a;
	if (b.charCodeAt(0) >= 48 && b.charCodeAt(0) <=57) {
		x = b + a;
	}
	var ext_id = document.getElementById("chk" + x);
	if(tick[parseInt(x)] == 0){
		ext_id.src = 'images/check_box_outline_blank-black-18dp.svg';
		document.getElementById("row" + x).style.backgroundColor = '#2A5368';
		tick[parseInt(x)] =1;
	}
	else{
		ext_id.src = 'images/check_box_outline_blank-black-18dp.svg';
		document.getElementById("row" + x).style.backgroundColor = '';
		tick[parseInt(x)] =0;
	}
}

