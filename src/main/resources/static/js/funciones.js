function validateFormNewGame() {
	const titleInput = document.getElementById('title');
	const descriptionInput = document.getElementById('description');
	const fileInput = document.getElementById('image');
	const maxSize = 1000000; // 1 MB

	// Validación de longitud del título
	if (titleInput.value.length < 3 || titleInput.value.length > 100) {
		titleInput.classList.add('is-invalid');
		return false; // Evita que se envíe el formulario
	} else {
		titleInput.classList.remove('is-invalid');
	}

	// Validación de longitud de la descripción
	if (descriptionInput.value.length < 4 || descriptionInput.value.length > 1200) {
		descriptionInput.classList.add('is-invalid');
		return false; // Evita que se envíe el formulario
	} else {
		descriptionInput.classList.remove('is-invalid');
	}

	// Validación del tamaño de la imagen
	if (fileInput.files.length > 0) {
		const fileSize = fileInput.files[0].size; // Tamaño del archivo en bytes
		const imageSizeError = document.getElementById('fileSizeError');
		const allowedExtensions = ['.png', '.jpg', '.jpeg'];
		const fileExtension = fileInput.value.substring(fileInput.value.lastIndexOf('.')).toLowerCase();

		if (!allowedExtensions.includes(fileExtension)) {
			imageSizeError.textContent = 'El archivo debe ser de tipo PNG, JPG o JPEG.';
			imageSizeError.style.display = 'block';
			fileInput.classList.add('is-invalid');
			return false; // Evita que se envíe el formulario
		} else if (fileSize > maxSize) {
			imageSizeError.textContent = 'El archivo debe tener un tamaño máximo de 1 MB.';
			imageSizeError.style.display = 'block';
			fileInput.classList.add('is-invalid');
			return false; // Evita que se envíe el formulario
		} else {
			imageSizeError.style.display = 'none';
			fileInput.classList.remove('is-invalid');
		}
	}

	return true; // Envía el formulario si todas las validaciones son válidas
}/**
 * 
 */	function validateFormReport() {
	const commentInput = document.getElementById('comment');

	// Validación de longitud de la descripción
	if (commentInput.value.length < 20 || commentInput.value.length > 100) {
		commentInput.classList.add('is-invalid');
		return false; // Evita que se envíe el formulario
	} else {
		descriptionInput.classList.remove('is-invalid');
	}

	return true; // Envía el formulario si todas las validaciones son válidas
}

function validateFormUpdateGame() {
	const titleInput = document.getElementById('title');
	const descriptionInput = document.getElementById('description');
	const fileInput = document.getElementById('image');
	const maxSize = 1000000; // 1 MB

	// Validación de longitud del título
	if (titleInput.value.length < 3 || titleInput.value.length > 100) {
		titleInput.classList.add('is-invalid');
		return false; // Evita que se envíe el formulario
	} else {
		titleInput.classList.remove('is-invalid');
	}

	// Validación de longitud de la descripción
	if (descriptionInput.value.length < 4 || descriptionInput.value.length > 500) {
		descriptionInput.classList.add('is-invalid');
		return false; // Evita que se envíe el formulario
	} else {
		descriptionInput.classList.remove('is-invalid');
	}

	// Validación del tamaño de la imagen
	if (fileInput.files.length > 0) {
		const fileSize = fileInput.files[0].size; // Tamaño del archivo en bytes
		const imageSizeError = document.getElementById('fileSizeError');

		if (fileSize > maxSize) {
			imageSizeError.style.display = 'block';
			fileInput.classList.add('is-invalid');
			return false; // Evita que se envíe el formulario
		} else {
			imageSizeError.style.display = 'none';
			fileInput.classList.remove('is-invalid');
		}
	}

	return true; // Envía el formulario si todas las validaciones son válidas
}


function validateFormNewProject() {
	const titleInput = document.getElementById('title');
	const descriptionInput = document.getElementById('description');
	const fileInput = document.getElementById('image');
	const maxSize = 1000000; // 1 MB

	// Validación de longitud del título
	if (titleInput.value.length < 3 || titleInput.value.length > 100) {
		titleInput.classList.add('is-invalid');
		return false; // Evita que se envíe el formulario
	} else {
		titleInput.classList.remove('is-invalid');
	}

	// Validación de longitud de la descripción
	if (descriptionInput.value.length < 4 || descriptionInput.value.length > 1200) {
		descriptionInput.classList.add('is-invalid');
		return false; // Evita que se envíe el formulario
	} else {
		descriptionInput.classList.remove('is-invalid');
	}

	// Validación del tamaño de la imagen
	if (fileInput.files.length > 0) {
		const fileSize = fileInput.files[0].size; // Tamaño del archivo en bytes
		const imageSizeError = document.getElementById('fileSizeError');
		const allowedExtensions = ['.png', '.jpg', '.jpeg'];
		const fileExtension = fileInput.value.substring(fileInput.value.lastIndexOf('.')).toLowerCase();

		if (!allowedExtensions.includes(fileExtension)) {
			imageSizeError.textContent = 'El archivo debe ser de tipo PNG, JPG o JPEG.';
			imageSizeError.style.display = 'block';
			fileInput.classList.add('is-invalid');
			return false; // Evita que se envíe el formulario
		} else if (fileSize > maxSize) {
			imageSizeError.textContent = 'El archivo debe tener un tamaño máximo de 1 MB.';
			imageSizeError.style.display = 'block';
			fileInput.classList.add('is-invalid');
			return false; // Evita que se envíe el formulario
		} else {
			imageSizeError.style.display = 'none';
			fileInput.classList.remove('is-invalid');
		}
	}

	return true; // Envía el formulario si todas las validaciones son válidas
}

function validateUser() {
	const pass = document.getElementById("pass").value;
	const repitPass = document.getElementById("repit-pass").value;
	// Si las contraseñas no coinciden, se establece un mensaje personalizado de validación
	if (pass != repitPass) {
		document.getElementById("repit-pass").setCustomValidity(
			"La contraseña no coincide o está vacía");
		return false;
	} else {
		// Si las contraseñas coinciden, se limpia el mensaje de validación personalizado
		document.getElementById("repit-pass").setCustomValidity("");
	}
	return true;
}

function validateAdmin() {
	const adminPass = document.getElementById("admin-pass").value;
	const repitAdminPass = document.getElementById("repit-admin-pass").value;
	// Si las contraseñas no coinciden, se establece un mensaje personalizado de validación
	if (adminPass != repitAdminPass) {
		document.getElementById("repit-admin-pass").setCustomValidity(
			"La contraseña no coincide o está vacía");
		return false;
	} else {
		// Si las contraseñas coinciden, se limpia el mensaje de validación personalizado
		document.getElementById("repit-admin-pass").setCustomValidity(
			"");
	}
	return adminPass !== '' && repitAdminPass !== '';
}

function validarFormulario() {

	// Obtiene el formulario al que se quiere aplicar estilos de validación de Bootstrap personalizados
	const form = document.querySelector('.needs-validation')

	// Evita el envío del formulario
	form.addEventListener('submit', function(event) {
		// Si el formulario no es válido, se previene el envío y se detiene la propagación del evento
		if (!form.checkValidity()) {
			event.preventDefault()
			event.stopPropagation()

			// Si el formulario es válido, pero no pasa la validación personalizada, se previene el envío
		} else if (!validate()) {
			event.preventDefault();
		}
		// Agrega la clase was-validated al formulario para mostrar los estilos de validación personalizados de Bootstrap
		form.classList.add('was-validated')
	}, false)
};