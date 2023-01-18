package ${packageName}.service.results;

import ${packageName}.entity.${ClassName};
import ${packageName}.entity.vo.${ClassName}VO;
import ${packageName}.entity.bo.${ClassName}BO;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import com.gb.utils.GeneralConvertor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * TODO ${functionName},Service返回实现
 * 代码生成器
 *
 * @author ${author}
 * @className ${ClassName}ServiceResults
 * @time ${date}
 */
@Slf4j
@Service
@Setter(onMethod_ = {@Autowired})
public class ${ClassName}ServiceResults {


    /**
     * TODO 单条，增强返回参数追加
     *
     * @param ${className}VO ${functionName}
     * @return ${ClassName}VO
     * @author ${author}
     * @methodName assignment
     * @time ${date}
     */
    public ${ClassName}VO assignment(${ClassName}VO ${className}VO) {
        if (${className}VO != null) {
            return ${className}VO;
        } else {
            return ${className}VO;
        }
    }


    /**
     * TODO 分页，增强返回参数追加
     *
     * @param ${className}VOList ${functionName}
     * @return Page<${ClassName}VO>
     * @author ${author}
     * @methodName assignment
     * @time ${date}
     */
    public Page<${ClassName}VO> assignment(Page<${ClassName}VO> ${className}VOList) {
        ${className}VOList.getRecords().forEach(${className}VO -> {
        });
        return ${className}VOList;
    }


    /**
     * TODO 集合，增强返回参数追加
     *
     * @param ${className}VOList ${functionName}
     * @return List<${ClassName}VO>
     * @author ${author}
     * @methodName assignment
     * @time ${date}
     */
    public List<${ClassName}VO> assignment(List<${ClassName}VO> ${className}VOList) {
        ${className}VOList.forEach(${className}VO -> {
        });
        return ${className}VOList;
    }


    /**
     * TODO DO转化VO
     *
     * @param pageDO ${functionName}
     * @return Page<${ClassName}VO>
     * @author ${author}
     * @methodName toPageVO
     * @time ${date}
     */
    public Page<${ClassName}VO> toPageVO(Page<${ClassName}> pageDO) {
        Page<${ClassName}VO> pageVO = new Page<${ClassName}VO>();
        pageVO.setRecords(GeneralConvertor.convertor(pageDO.getRecords(), ${ClassName}VO.class));
        pageVO.setCurrent(pageDO.getCurrent());
        pageVO.setPages(pageDO.getPages());
        pageVO.setSize(pageDO.getSize());
        pageVO.setTotal(pageDO.getTotal());
        return pageVO;
    }
}