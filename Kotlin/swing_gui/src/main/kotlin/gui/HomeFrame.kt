package gui

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*

class HomeFrame : JFrame() {
    private val componentWidth = 100
    init {
        layout = BorderLayout()
        size = Dimension(700, 350)
        add(JPanel().apply {
            layout = FlowLayout(FlowLayout.LEFT, 0 ,2)
            background = this.background.brighter()
            preferredSize = Dimension(componentWidth, 0)
            add(JPanel().apply {
                background = Color.LIGHT_GRAY
                preferredSize = Dimension(componentWidth, 50)
                add(JLabel("Velkommen"))
            })
            add(customButton("Button 1").apply {
                addActionListener {
                    println("Knapper én")
                }
            })
            add(customButton("Button 2"))
            add(customButton("Button 3"))
            add(customButton("Button 4"))
            add(Box.createVerticalGlue())
            BorderLayout.WEST
        })
    }

    private fun customButton(text: String) : JButton {
        return JButton(text).apply {
            background = background.darker()
            preferredSize = Dimension(componentWidth, 28)
            isBorderPainted = false
            isFocusPainted = false


        }
    }
}