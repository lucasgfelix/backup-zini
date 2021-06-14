function modalon(){
    var modal = document.getElementById('modal');
    modal.style.display = 'block';
    
    var acc1 = document.getElementById('accordion1');
    var acc2 = document.getElementById('accordion2');
    var acc3 = document.getElementById('accordion3');
    var acc4 = document.getElementById('accordion4');
    var acc5 = document.getElementById('accordion5');
    var panel1, panel2, panel3, panel4, panel5;
    

    acc1.addEventListener('click', function(){
        this.classList.toggle('active');
        panel1 = document.getElementById('panel1');
        if(panel1.style.maxHeight){
         panel1.style.maxHeight = null;
        }else{
         panel1.style.maxHeight = panel1.scrollHeight + 'px';
         panel2.style.maxHeight = null;
         panel3.style.maxHeight = null;
         panel4.style.maxHeight = null;
         panel5.style.maxHeight = null;
        }
        
    })
    acc2.addEventListener('click', function(){
        this.classList.toggle('active');
        panel2 = document.getElementById('panel2');
        if(panel2.style.maxHeight){
         panel2.style.maxHeight = null;
        }else{
         panel2.style.maxHeight = panel2.scrollHeight + 'px';
         panel1.style.maxHeight = null;
         panel3.style.maxHeight = null;
         panel4.style.maxHeight = null;
         panel5.style.maxHeight = null;

        }
        
    })

    acc3.addEventListener('click', function(){
        this.classList.toggle('active');
        panel3 = document.getElementById('panel3');
        if(panel3.style.maxHeight){
         panel3.style.maxHeight = null;
        }else{
         panel3.style.maxHeight = panel3.scrollHeight + 'px';
         panel2.style.maxHeight = null;
         panel4.style.maxHeight = null;
         panel5.style.maxHeight = null;
         panel1.style.maxHeight = null;
        }
        
    })

    acc4.addEventListener('click', function(){
        this.classList.toggle('active');
        panel4 = document.getElementById('panel4');
        if(panel4.style.maxHeight){
         panel4.style.maxHeight = null;
        }else{
         panel4.style.maxHeight = panel4.scrollHeight + 'px';
         panel1.style.maxHeight = null;
         panel2.style.maxHeight = null;
         panel3.style.maxHeight = null;
         panel5.style.maxHeight = null;
         
        }
        
    })

    acc5.addEventListener('click', function(){
        this.classList.toggle('active');
        panel5 = document.getElementById('panel5');

        if(panel5.style.maxHeight){
         panel5.style.maxHeight = null;
        }else{
         panel5.style.maxHeight = panel5.scrollHeight + 'px';
         panel1.style.maxHeight = null;
         panel2.style.maxHeight = null;
         panel3.style.maxHeight = null;
         panel4.style.maxHeight = null;
        }
        
    })
}
function btsair(){
    var modal = document.getElementById('modal');
    modal.style.display = 'none';
}
