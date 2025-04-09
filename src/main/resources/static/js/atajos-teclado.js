/**
 * 
 */// Archivo de atajos de teclado para mejorar la accesibilidad
window.onload =  function() {
	
  // Elemento para mostrar información sobre los atajos
  // Las opciones exclusivas del admin las dejo desactivadas para evitar conflictos
  const shortcutsInfo = document.createElement('div');
  shortcutsInfo.innerHTML = `
    <div id="keyboard-shortcuts-info" style="display: none; position: fixed; bottom: 20px; left: 20px; 
         background-color: rgba(0,0,0,0.8); color: white; padding: 15px; border-radius: 5px; z-index: 1000;">
      <h5>Atajos de teclado</h5>
      <ul style="list-style: none; padding-left: 10px;">
        <li>Alt + J: Juegos</li>
        <!--<li>Alt + Y: Nuevo juego</li>-->
        <!--<li>Alt + R: Juegos denunciados</li>-->
        <li>Alt + P: Proyectos</li>
        <li>Alt + N: Nuevo proyecto</li>
        <li>Alt + M: Mis proyectos</li>
        <!--<li>Alt + U: Usuarios</li>-->
        <li>Alt + F: Mi perfil</li>
        <li>Alt + I: Mostrar/ocultar esta información</li>
      </ul>
    </div>
  `;
  document.body.appendChild(shortcutsInfo);
  
  // Función para mostrar un mensaje de feedback al usuario
  function showFeedback(message) {
    const feedback = document.createElement('div');
    feedback.style.position = 'fixed';
    feedback.style.top = '50px';
    feedback.style.left = '50%';
    feedback.style.transform = 'translateX(-50%)';
    feedback.style.backgroundColor = 'rgba(0,0,0,0.7)';
    feedback.style.color = 'white';
    feedback.style.padding = '10px 20px';
    feedback.style.borderRadius = '5px';
    feedback.style.zIndex = '9999';
    feedback.textContent = message;
    
    document.body.appendChild(feedback);
    
    setTimeout(() => {
      feedback.style.opacity = '0';
      feedback.style.transition = 'opacity 0.5s ease';
      setTimeout(() => document.body.removeChild(feedback), 500);
    }, 1500);
  }
  
  // Manejador de eventos para las teclas
  document.addEventListener('keydown', function(event) {
    // Verificamos si se presiona Alt + una letra específica
    if (event.altKey) {
      let navigateTo = null;
      let feedbackMessage = '';
      
      switch(event.key) {
        case 'j': // Alt + J para "Juegos"
          navigateTo = '/gameList';
          feedbackMessage = 'Navegando a Juegos...';
          break;
//        case 'y': // Alt + Y para "Nuevo juego"
//          navigateTo = '/newGame';
//          feedbackMessage = 'Navegando a Nuevo juego...';
//          break;
//        case 'r': // Alt + R para "Juegos denunciados"
//          navigateTo = '/reportedGames';
//          feedbackMessage = 'Navegando a Juegos denunciados...';
//          break;
        case 'p': // Alt + P para "Proyectos"
          navigateTo = '/projectList';
          feedbackMessage = 'Navegando a Proyectos...';
          break;
        case 'n': // Alt + N para "Nuevo proyecto"
          navigateTo = '/newProject';
          feedbackMessage = 'Navegando a Nuevo proyecto...';
          break;
        case 'm': // Alt + M para "Mis proyectos"
          navigateTo = '/myProjects';
          feedbackMessage = 'Navegando a Mis proyectos...';
          break;
//        case 'u': // Alt + U para "Usuarios"
//          navigateTo = '/usersList';
//          feedbackMessage = 'Navegando a Usuarios...';
//          break;
        case 'f': // Alt + F para "Mi perfil"
          navigateTo = '/user/profile';
          feedbackMessage = 'Navegando a Mi perfil...';
          break;
        case 'i': // Alt + I para mostrar/ocultar la ayuda de atajos
          event.preventDefault();
          const infoPanel = document.getElementById('keyboard-shortcuts-info');
          infoPanel.style.display = infoPanel.style.display === 'none' ? 'block' : 'none';
          return;
      }
      
      if (navigateTo) {
        event.preventDefault();
        showFeedback(feedbackMessage);
        // Pequeño retraso para que el usuario vea el feedback antes de navegar
        setTimeout(() => {
          window.location.href = navigateTo;
        }, 300);
      }
    }
  });
  
  // Añadir atributos aria para mejorar la accesibilidad
  const navLinks = document.querySelectorAll('.nav-link');
  navLinks.forEach(link => {
    const href = link.getAttribute('href');
    let shortcutText = '';
    
    switch(href) {
      case '/gameList':
        shortcutText = 'Alt+J';
        break;
      case '/newGame':
        shortcutText = 'Alt+Y';
        break;
      case '/reportedGames':
        shortcutText = 'Alt+R';
        break;
      case '/projectList':
        shortcutText = 'Alt+P';
        break;
      case '/newProject':
        shortcutText = 'Alt+N';
        break;
      case '/myProjects':
        shortcutText = 'Alt+M';
        break;
      case '/usersList':
        shortcutText = 'Alt+U';
        break;
    }
    
    if (shortcutText) {
      link.setAttribute('aria-keyshortcuts', shortcutText);
      link.setAttribute('title', link.textContent + ' (' + shortcutText + ')');
    }
  });
  
  // Añadir un botón para mostrar la ayuda de atajos
  const helpButton = document.createElement('button');
  helpButton.innerHTML = '⌨️';
  helpButton.style.position = 'fixed';
  helpButton.style.bottom = '35px';
  helpButton.style.left = '5px';
  helpButton.style.backgroundColor = '#007bff';
  helpButton.style.color = 'white';
  helpButton.style.border = 'none';
  helpButton.style.borderRadius = '50%';
  helpButton.style.width = '40px';
  helpButton.style.height = '40px';
  helpButton.style.fontSize = '20px';
  helpButton.style.cursor = 'pointer';
  helpButton.style.zIndex = '999';
  helpButton.title = 'Mostrar atajos de teclado';
  helpButton.setAttribute('aria-label', 'Mostrar atajos de teclado');
  
  helpButton.addEventListener('click', function() {
    const infoPanel = document.getElementById('keyboard-shortcuts-info');
    infoPanel.style.display = infoPanel.style.display === 'none' ? 'block' : 'none';
  });
  
  document.body.appendChild(helpButton);
};