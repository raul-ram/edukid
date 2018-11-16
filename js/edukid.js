$(document).ready(function () {
  console.log('ready!')
  // inicializamos la libreria Artyom
  function porDefecto() {
    artyom.initialize({
      lang: 'es-ES',
      continuous: true,
      speed: 1
    })
  }
  porDefecto();
  //Mensaje de bienvenida
  artyom.say("Bienvenidos Al sistema inteligente EduKid");
})

function startArtyom() {
  artyom.initialize({
    lang: 'es-Es',
    continuous: true, // Reconoce 1 solo comando y para de escuchar
    listen: true, // Iniciar !
    debug: true, // Muestra un informe en la consola
    speed: 1 // Habla normalmente
  })
  artyom.say("Micrófono activado");
}

function CambiarIdioma(idioma){
  artyom.fatality() // Detener cualquier instancia previa
  artyom.initialize({
    lang: idioma,
    continuous: true, 
    debug: true, 
    speed: 1 
  })
  artyom.say("1")
}

var inicio = '<div class="vocal-item"><ul class="list-group"><li class="list-group-item active">La letra</li><li class="list-group-item"><a class="itemimg">'
var img = '<img onclick="GetId(this)" src="img/vocal/'
var final = 'alt=""></a></li>		<li class="list-group-item"></li></ul></div>'

var vocales = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
  'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
]
$('#VerVocales').on('click', function () {
  $('.vocal-container').empty()
  for (let i = 1; i <= 26; i++) {
    $('.vocal-container').append(inicio + img + i + '.png"' + 'id="' + vocales[i - 1] + '"' + final)
    console.log(i + ' ' + vocales[i - 1])
  }
})

var inicioNum =
  '<div class="vocal-item"><ul class="list-group"><li class="list-group-item active">El Numero</li><li class="list-group-item"><a class="itemimg">'
var imgNum = '<img onclick="GetId(this)" src="img/numero/'

var numero = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '10']
$('#VerNuneros').on('click', function () {
  $('.vocal-container').empty()
  for (let i = 0; i <= 10; i++) {
    $('.vocal-container').append(inicioNum + imgNum + i + '.png"' + 'id="' + numero[i] + '"' + final)
    console.log(i + ' ' + vocales[i - 1])
  }
})

// Cuando precione un imagen
function GetId(img) {
  console.log(img)
  var id = $(img).attr('id')
  console.log(id)
  artyom.say(id)
}

$('.imgv').mouseover(function () {
  console.log(this)
})

// Botones
$('#VerNuneros').mouseover(function () {
  artyom.say('Aprender los numeros')
})
$('#VerVocales').mouseover(function () {
  artyom.say('Aprender el abecedario')
})

// opciones menu
$('#mInfo').mouseover(function () {
  artyom.say('Ir a informacion')
})
$('#mAprender').mouseover(function () {
  artyom.say('Ir a Aprender el abcedario y los números')
})
$('#mConverter').mouseover(function () {
  artyom.say('Ir a Convertidor')
})
$('#mContacto').mouseover(function () {
  artyom.say('Ir a conctactos')
})

// compartir
$('#compartir').mouseover(function () {
  artyom.say('Comparte el enlace por whatsapp')
})

$('#uno').mouseover(function () {
  artyom.say('Facebook')
})
$('#uno').mouseout(function () {
  artyom.shutUp()
})

$('#dos').mouseover(function () {
  artyom.say('Google')
})

$('#tres').mouseover(function () {
  artyom.say('Instagram')
})

$('#cuatro').mouseover(function () {
  artyom.say('Youtube')
})

// El sistema responde
artyom.addCommands([{
    indexes: ['buenos días', 'cuál es tu banda favorita', 'Saluda a todos'],
    action: function (i) {
      if (i == 0) {
        artyom.say('Hola Raul, buenos dias')
      }
      if (i == 1) {
        artyom.say('Raul, me gusta Guns And Roses')
      }
      if (i == 2) {
        artyom.say('Hola gente')
      }
    }
  },
  {
    indexes: ['me voy', 'chau'],
    action: function () {
      alert('ok, chau')
    }
  },
  {
    indexes: ['abrir google', 'abrir facebook', 'abrir youtube'],
    action: function (i) {
      if (i == 0) {
        artyom.say('Abriendo google')
        window.open('http://www.google.com', '_blank')
      }
      if (i == 1) {
        artyom.say('Abriendo facebook')
        window.open('http://www.facebook.com/', '_blank')
      }
      if (i == 2) {
        artyom.say('Abriendo youtube')
        window.open('https://www.youtube.com/channel/UC4S0toSbH7rYmYF4c2nj0xQ', '_blank')
      }
    }
  },
  {
    indexes: ['limpiar'],
    action: function () {
      $('#salida').val('')
    }
  }
])

// Escribir lo que escucha.
artyom.redirectRecognizedTextOutput(function (text, isFinal) {
  var texto = $('#salida')
  if (isFinal) {
    texto.val(text)
    console.log(text)
  } else {}
})

// Stop libreria
function stopArtyom() {
  artyom.say("Apagando micrófono");
  artyom.fatality() // Detener cualquier instancia previa
}

// leer texto
$('#btnLeer').click(function (e) {
  e.preventDefault()
  var btn = $('#btnLeer')
  if (artyom.speechSupported()) {
    btn.addClass('disabled')
    btn.attr('disabled', 'disabled')

    var text = $('#leer').val()
    if (text) {
      var lines = $('#leer').val().split('\n').filter(function (e) {
        return e
      })
      var totalLines = lines.length - 1

      for (var c = 0; c < lines.length; c++) {
        var line = lines[c]
        if (totalLines == c) {
          artyom.say(line, {
            onEnd: function () {
              btn.removeAttr('disabled')
              btn.removeClass('disabled')
            }
          })
        } else {
          artyom.say(line)
        }
      }
    }
  } else {
    alert('Tu navegador no es compatible !')
  }
})