package com.jwar.github_repo.ui.dashboard

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.jwar.github_repo.R
import com.jwar.github_repo.databinding.ViewDashboardCardBinding

class DashboardCardView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : CardView(ctx, attrs, defStyleAttr) {

    private var binding: ViewDashboardCardBinding =
        ViewDashboardCardBinding.inflate(LayoutInflater.from(context), this, true)

    private val cardView by lazy { binding.cardView }
    private val textViewCount by lazy { binding.textViewCount }
    private val textViewTitle by lazy { binding.textViewTitle }
    private val imageViewIcon by lazy { binding.imageViewIcon }

    private var attributes: TypedArray = context.theme.obtainStyledAttributes(
        attrs, R.styleable.DashboardCard, 0, 0
    )

    private val cardColor by lazy { attributes.getColor(R.styleable.DashboardCard_card_color, Color.WHITE) }
    private val textColor by lazy { attributes.getColor(R.styleable.DashboardCard_card_text_color, Color.WHITE) }
    private val count by lazy { attributes.getInteger(R.styleable.DashboardCard_card_count, 0) }
    private val title by lazy { attributes.getString(R.styleable.DashboardCard_card_title) }
    private val icon by lazy { attributes.getDrawable(R.styleable.DashboardCard_card_icon) }

    init {
        cardColor(cardColor)
        textColor(textColor)
        cardCount(count)
        cardTitle(title)
        cardIcon(icon)
    }

    fun cardColor(color: Int) {
        cardView.setBackgroundColor(color)
    }

    fun textColor(color: Int) {
        textViewCount.setTextColor(color)
        textViewTitle.setTextColor(color)
    }

    fun cardCount(count: Int) {
        textViewCount.text = "$count"
    }

    fun cardTitle(title: String?) {
        textViewTitle.text = title
    }

    fun cardIcon(icon: Drawable?) {
        imageViewIcon.setImageDrawable(icon)
    }

}