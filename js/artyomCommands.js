(function(window){
    'use strict';

    /**
     * Example Artyom Commands
     * @type Array
     */
    var artyomCommands = [
        //Simple Command Example
        {
            indexes: ['edu'],
            action : function(i){
                artyom.say("Hola, aqui estoy te escucho, yo soy EduKid",{
                    onStart: function(){
                        console.log("Speaking presentation");
                    },
                    onEnd: function(){
                        console.log("All that i've to say has been said.");
                    }
                });
            }
        },
        //Smart Command Example
        {
            indexes: ['pronunciate * please'],
            smart:true,
            action : function(i,wildcard,recognized_text){
                console.log("Recognized : " + recognized_text,"Wildcard : "+wildcard);
                artyom.say(wildcard);
            }
        }
        //Continue adding your own commands here
    ];

    artyom.addCommands(artyomCommands);

    /**
     * Or use the shorter and cleaner method :
     */

    artyom.on(['Good morning']).then(function(i){
        alert("Good morning ! How are you?");
    });

    artyom.on(['Repeat after me *'] , true).then(function(i, wildcard){
        alert("You've said " + wildcard);
    });

    console.log(artyom.getAvailableCommands());
})(window);
