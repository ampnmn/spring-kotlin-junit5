package com.ampnmn.springkotlinjunit5.controller

import com.ampnmn.springkotlinjunit5.controller.form.Board3x3Form
import com.ampnmn.springkotlinjunit5.model.Board3x3
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("3x3")
class Board3x3Controller {

    @GetMapping
    fun show(@ModelAttribute form: Board3x3Form, model: Model): String {
        val data = arrayOf(
                arrayOf(9, 2, 0, 0, 1, 0, 3, 0, 0),
                arrayOf(8, 5, 0, 0, 9, 0, 0, 2, 0),
                arrayOf(0, 0, 3, 0, 0, 0, 0, 0, 0),

                arrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0),
                arrayOf(3, 0, 0, 0, 0, 1, 6, 0, 0),
                arrayOf(1, 9, 7, 0, 0, 0, 2, 5, 0),

                arrayOf(0, 0, 0, 5, 0, 9, 0, 6, 2),
                arrayOf(0, 8, 5, 0, 2, 0, 4, 0, 0),
                arrayOf(0, 0, 9, 7, 4, 0, 0, 3, 0)
        )
        form.board = data
        model.addAttribute("form", form)
        return "number-place"
    }

    @PostMapping(params = ["help"])
    fun help(@ModelAttribute form: Board3x3Form, model: Model): String {
        val board = Board3x3()
        board.init(form.board)
        board.fillAuto()

        model.addAttribute("form", form.setBoard(board))
        return "number-place"
    }

    @PostMapping(params = ["re:start"])
    fun restart(@ModelAttribute form: Board3x3Form, model: Model) = "redirect:/3x3"
}
