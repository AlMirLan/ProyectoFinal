package com.proyecto.proyecto.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.proyecto.proyecto.dto.BoardGameDTO;
import com.proyecto.proyecto.dto.ReportDTO;
import com.proyecto.proyecto.model.BoardGame;
import com.proyecto.proyecto.model.Report;
import com.proyecto.proyecto.model.Review;
import com.proyecto.proyecto.service.GameManagementServiceI;
import com.proyecto.proyecto.service.ReviewManagementServiceI;
import com.proyecto.proyecto.service.UserManagementServiceI;
import com.proyecto.proyecto.storage.StorageService;


/**
 * Clase controlador de los reportes en PDF
 * 
 * @author Alejandro_Mirabent_Landero
 *
 */
public class pdfReportsController {

}
