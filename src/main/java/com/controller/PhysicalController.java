package com.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.PhysicalDetailDto;
import com.entity.PhysicalDetail;
import com.form.PhysicalDetailForm;
import com.security.UserDetailsImpl;
import com.service.PhysicalService;
import com.validation.All;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/physicals")
public class PhysicalController {

	private PhysicalService physicalService;

	@GetMapping("/create")
	public String create(@ModelAttribute PhysicalDetailForm physicalDetailForm, Model model,
			@AuthenticationPrincipal UserDetailsImpl user) {
		physicalDetailForm.setRecordedDate(LocalDate.now());
		model.addAttribute("screen", "physical/create :: create_contents");
		return "layout";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute @Validated(All.class) PhysicalDetailForm physicalDetailForm, BindingResult br, Model model,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetailsImpl user) {
		if (br.hasErrors()) {
			return create(physicalDetailForm, model, user);
		}
		PhysicalDetailDto physicalDto = new PhysicalDetailDto();
		BeanUtils.copyProperties(physicalDetailForm, physicalDto);
		physicalDto.setUserId(user.getId());
		try {
			this.physicalService.create(physicalDto);
		} catch (DuplicateKeyException e) {
			model.addAttribute("msg", e.getMessage());
			return create(physicalDetailForm, model, user);
		} catch (Exception e) {
			model.addAttribute("msg", "create error");
			return "error";
		}
		redirectAttributes.addFlashAttribute("msg", "recorded your weight.");
		return "redirect:/home";
	}

	@GetMapping("/{userId}")
	public String getList(Model model, Pageable pageable, @AuthenticationPrincipal UserDetailsImpl user) {
		Page<PhysicalDetail> page = this.physicalService.getPhysicalDetails(pageable, user);
		model.addAttribute("page", page);
		if (page.getTotalPages() == 0) {
			model.addAttribute("screen", "physical/listnodata :: listnodata_contents");
			return "layout";
		}
		model.addAttribute("content", page.getContent());
		model.addAttribute("screen", "physical/list :: list_contents");
		return "layout";
	}
	
	@GetMapping("/edit/{createdAt}")
	public String edit(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt,
			@ModelAttribute PhysicalDetailForm physicalDetailForm, Model model, @AuthenticationPrincipal UserDetailsImpl user) {
		model.addAttribute("screen", "physical/edit :: edit_contents");
		PhysicalDetail selectedPhysicalDetail = this.physicalService.getPhysicalDetail(createdAt, user.getId());
		BeanUtils.copyProperties(selectedPhysicalDetail, physicalDetailForm);
		return "layout";
	}

	@PostMapping("/edit/{createdAt}")
	public String edit(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt,
			@ModelAttribute @Validated(All.class) PhysicalDetailForm physicalDetailForm, BindingResult br, Model model,
			RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetailsImpl user) {
		if (br.hasErrors()) {
			return edit(createdAt, physicalDetailForm, model, user);
		}
		PhysicalDetailDto physicalDto = new PhysicalDetailDto();
		BeanUtils.copyProperties(physicalDetailForm, physicalDto);
		physicalDto.setCreatedAt(createdAt);
		physicalDto.setUserId(user.getId());
		try {
			this.physicalService.edit(physicalDto);
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("msg", e.getMessage());
			return edit(createdAt, physicalDetailForm, model, user);
		} catch (Exception e) {
			model.addAttribute("msg", "edit error");
			return "error";
		}
		redirectAttributes.addFlashAttribute("msg", "edited your weight.");
		return "redirect:/physicals/" + user.getId();
	}

	@PostMapping("/delete/{createdAt}")
	public String delete(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt,
			RedirectAttributes redirectAttributes, Model model, @AuthenticationPrincipal UserDetailsImpl user) {
		try {
			this.physicalService.delete(createdAt, user.getId());
		} catch (EmptyResultDataAccessException e) {
			model.addAttribute("msg", e.getMessage());
			return "redirect:/read";
		} catch (Exception e) {
			model.addAttribute("msg", "delete error");
			return "error";
		}
		redirectAttributes.addFlashAttribute("msg", "deleted your weight.");
		return "redirect:/physicals/" + user.getId();
	}

}
