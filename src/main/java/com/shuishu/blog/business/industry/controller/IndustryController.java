package com.shuishu.blog.business.industry.controller;


import com.shuishu.blog.business.industry.service.IndustryService;
import com.shuishu.blog.common.config.base.ApiResponse;
import com.shuishu.blog.common.config.base.PageDTO;
import com.shuishu.blog.common.domain.industry.entity.dto.IndustryDTO;
import com.shuishu.blog.common.domain.industry.entity.vo.IndustryVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author ：谁书-ss
 * @Date ：2023-04-03 22:34
 * @IDE ：IntelliJ IDEA
 * @Motto ：ABC(Always Be Coding)
 * <p></p>
 * @Description ：行业
 * <p></p>
 */
@Tag(name = "行业")
@RequiredArgsConstructor
@RestController
@RequestMapping("industry")
public class IndustryController {
    private final IndustryService industryService;


    @PostMapping("add")
    public ApiResponse<String> addIndustry(@RequestBody IndustryDTO industryDTO) {
        industryService.addIndustry(industryDTO);
        return ApiResponse.success();
    }

    @GetMapping("page")
    public ApiResponse<Page<IndustryVO>> findIndustryPage(IndustryDTO industryDTO, PageDTO pageDTO) {
        return ApiResponse.of(industryService.findIndustryPage(industryDTO, pageDTO));
    }

    @GetMapping("details")
    public ApiResponse<IndustryVO> findIndustryDetails(Long industryId) {
        return ApiResponse.of(industryService.findIndustryDetails(industryId));
    }

    @PostMapping("update")
    public ApiResponse<String> updateIndustry(@RequestBody IndustryDTO industryDTO) {
        industryService.updateIndustry(industryDTO);
        return ApiResponse.success();
    }

}
