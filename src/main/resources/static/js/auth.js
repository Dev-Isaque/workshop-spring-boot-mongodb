function login() {
    var email = $("#email").val();
    var password = $("#password").val();

    $.post("/api/auth/login", { email, password }, function(response) {
        if (response.userId) {
            localStorage.setItem("userId", response.userId);
            localStorage.setItem("email", email);
            window.location.href = "/index"; 
        } else {
            alert(response.message);
        }
    });
}


function register() {
    var name = $("#name").val();
    var cpf = $("#cpf").val();
    var data_nasc = $("#data_nasc").val();
    var sexo = $("#sexo").val();
    var telefone = $("#telefone").val();
    var cep = $("#cep").val();
    var endereco = $("#endereco").val();
    var bairro = $("#bairro").val();
    var cidade = $("#cidade").val();
    var email = $("#email").val();
    var password = $("#senha").val();
    var confirmarSenha = $("#confirmar_senha").val();

    // Validação: verificar se todos os campos estão preenchidos
    if (!name || !cpf || !data_nasc || !sexo || !telefone || !cep || !endereco || 
        !bairro || !cidade || !email || !password || !confirmarSenha) {
        alert("Por favor, preencha todos os campos!");
        return;
    }

    // Validação: verificar se as senhas coincidem
    if (password !== confirmarSenha) {
        alert("As senhas não coincidem!");
        return;
    }

    // Criar objeto com os dados do usuário
    var userData = {
        name: name,
        cpf: cpf,
        data_nasc: data_nasc,
        sexo: sexo,
        telefone: telefone,
        cep: cep,
        endereco: endereco,
        bairro: bairro,
        cidade: cidade,
        email: email,
        password: password
    };

    // Enviar requisição AJAX para a API de registro
    $.post("/api/auth/register", userData)
        .done(function(response) {
            if (response.success) {
                alert("Usuário cadastrado com sucesso!");
                window.location.href = "login.html";
            } else {
                alert(response.message || "Erro ao registrar.");
            }
        })
        .fail(function() {
            alert("Erro ao conectar ao servidor.");
        });
}
