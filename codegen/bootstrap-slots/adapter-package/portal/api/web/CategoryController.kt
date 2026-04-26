package {{ basePackage }}.adapter.portal.api.web

import cn.dev33.satoken.annotation.SaIgnore
import com.only4.cap4k.ddd.core.Mediator
import {{ basePackage }}.adapter.portal.api.payload.category.GetCategoryTree
import {{ basePackage }}.application.queries.category.GetCategoryTreeQry
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SaIgnore
@RestController
@RequestMapping("/category")
class CategoryController {

    @PostMapping("/getCategoryTree")
    fun getCategoryTree(): List<GetCategoryTree.Response> {
        TODO("Pending controller adapter contract implementation.")
    }
}
