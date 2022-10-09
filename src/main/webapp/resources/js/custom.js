/**
 * Contains custom JavaScript code
 */
var urlHolder = new Object();


function loadTable() {
    toggleForms('hide');
    $.get(urlHolder.records, function(response) {

        $('#tableBooks').find('tbody').remove();

        for (var i=0; i<response.books.length; i++) {

            var row = '<tr>';
            row += '<td><input type="radio" name="index" id="index" value="'+response.books[i]+'"></td>';
            row += '<td>' + response.books[i].name + '</td>';
            row += '<td>' + response.books[i].author + '</td>';
            row += '</tr>';
            $('#tableBooks').append(row);
        }

        $('#tableBooks').data('model', response.books);

    });
}

function submitNewRecord() {
    $.post(urlHolder.add, {
            name: $('#newBook').val(),
            author: $('#newAuthor').val()
        },
        function(response) {
            if (response != null) {
                loadTable();
                toggleForms('hide'); ;
                toggleCrudButtons('show');
                alert('Başarılı! Data Eklendi.');
            } else {
                alert('Başarısız! Bir Hata Saptandı!');
            }
        }
    );
}

function submitDeleteRecord() {
    var selected = $('input:radio[name=index]:checked').val();

    $.post(urlHolder.del, {
            name: $('#tableBooks').data('model')[selected].name
        },
        function(response) {
            if (response == true) {
                loadTable(urlHolder.records);
                alert('Başarılı! Data Silindi.');
            } else {
                alert('Başarısız! Bir Hata Saptandı!');
            }
        }
    );
}

function submitUpdateRecord() {
    $.post(urlHolder.edit, {
            name: $('#editBookName').val(),
            author: $('#editAuthor').val()
        },
        function(response) {
            if (response != null) {
                loadTable();
                toggleForms('hide'); ;
                toggleCrudButtons('show');
                alert('Başarılı! Data Güncellendi.');
            } else {
                alert('Başarısız! Bir Hata Saptandı!');
            }
        }
    );
}


function hasSelected() {
    var selected = $('input:radio[name=index]:checked').val();
    if (selected == undefined) {
        alert('Bir Kayıt Seçildi!');
        return false;
    }

    return true;
}

function fillEditForm() {
    var selected = $('input:radio[name=index]:checked').val();
    $('#editBookName').val( $('#tableBooks').data('model')[selected].name );
    $('#editAuthor').val( $('#tableBooks').data('model')[selected].author );
}

function resetNewForm() {
    $('#newBook').val('');
    $('#newAuthor').val('');
}

function resetEditForm() {
    $('#editBookName').val('');
    $('#editAuthor').val('');
}

function toggleForms(id) {
    if (id == 'hide') {
        $('#newForm').hide();
        $('#editForm').hide();

    } else if (id == 'new') {
        resetNewForm();
        $('#newForm').show();
        $('#editForm').hide();

    } else if (id == 'edit') {
        resetEditForm();
        $('#newForm').hide();
        $('#editForm').show();
    }
}

function toggleCrudButtons(id) {
    if (id == 'show') {
        $('#newBtn').removeAttr('disabled');
        $('#editBtn').removeAttr('disabled');
        $('#deleteBtn').removeAttr('disabled');
        $('#reloadBtn').removeAttr('disabled');

    } else if (id == 'hide'){
        $('#newBtn').attr('disabled', 'disabled');
        $('#editBtn').attr('disabled', 'disabled');
        $('#deleteBtn').attr('disabled', 'disabled');
        $('#reloadBtn').attr('disabled', 'disabled');
    }
}
