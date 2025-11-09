package com.plant.way

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Start21Activity : AppCompatActivity() {

    private lateinit var button1: TextView
    private lateinit var button2: TextView
    private lateinit var button3: TextView
    private lateinit var button4: TextView

    private lateinit var item1: ImageView
    private lateinit var item2: ImageView
    private lateinit var item3: ImageView
    private lateinit var item4: ImageView

    private var currentSelectedButton = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start21)

        initViews()
        setupClickListeners()
    }

    private fun initViews() {
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        item1 = findViewById(R.id.item1)
        item2 = findViewById(R.id.item2)
        item3 = findViewById(R.id.item3)
        item4 = findViewById(R.id.item4)

        // Setup back button
        findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            finish()
        }
    }

    private fun setupClickListeners() {
        button1.setOnClickListener {
            selectButton(1)
            updateItems(1)
        }

        button2.setOnClickListener {
            selectButton(2)
            updateItems(2)
        }

        button3.setOnClickListener {
            selectButton(3)
            updateItems(3)
        }

        button4.setOnClickListener {
            selectButton(4)
            updateItems(4)
        }

        // Setup item click listeners
        setupItemClickListeners()
    }

    private fun setupItemClickListeners() {
        item1.setOnClickListener {
            openPlantDetail(currentSelectedButton, 1)
        }

        item2.setOnClickListener {
            openPlantDetail(currentSelectedButton, 2)
        }

        item3.setOnClickListener {
            openPlantDetail(currentSelectedButton, 3)
        }

        item4.setOnClickListener {
            openPlantDetail(currentSelectedButton, 4)
        }
    }

    private fun openPlantDetail(categoryNumber: Int, itemNumber: Int) {
        val plantDetail = createPlantDetailEntity(categoryNumber, itemNumber)
        val intent = Intent(this, Start22Activity::class.java)
        intent.putExtra(Start22Activity.EXTRA_PLANT_DETAIL, plantDetail)
        startActivity(intent)
    }

    private fun createPlantDetailEntity(categoryNumber: Int, itemNumber: Int): PlantDetailEntity {
        // TODO: 这里先返回示例数据，后续会替换为实际数据
        return when (categoryNumber) {
            1 -> { // Balcony fruits
                when (itemNumber) {
                    1 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top1,
                        plantName = "lemon",
                        wateringText = "Lemon roots are susceptible to waterlogging, so avoid overwatering. Water thoroughly only after the topsoil has dried out.",
                        fertilizingText = "When repotting or preparing the soil, sprinkle some bone meal to promote flowering. During the growing season (spring and summer), bury 10 slow-release fertilizer pellets around the edge of the pot; no further fertilization is needed for the next 3 months. During the flowering and fruiting period, switch to potassium dihydrogen phosphate. It's better to err on the side of low concentration rather than high concentration.",
                        pruningText = "In spring (March-April): Prune old, weak, and crossing branches to encourage rapid new growth. After harvesting, prune damaged branches to help the plant conserve energy.",
                        pestAndDiseasePreventionText = "Maintain good ventilation. If pests or diseases are found, use physical control methods (such as sticky traps) and isolate infected plants. Soil Requirements: Lemons require loose, well-draining, slightly acidic soil.",
                        lightAndTemperatureText = "Lemons need plenty of sunlight, at least 6 hours a day. Provide some shade during midday in summer to prevent sunburn. Lemons prefer warmth, ideally between 15-25°C; temperatures should not fall below 5°C in winter.",
                        soilRequirementsText = "Lemons require loose, well-draining, slightly acidic soil."
                    )
                    2 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top2,
                        plantName = "Strawberry",
                        wateringText = "Do not overwater strawberries. Water only when the surface soil is dry. Avoid spraying water on the leaves; keep them dry.",
                        fertilizingText = "During the fruiting period, apply potassium dihydrogen phosphate (1:1000) weekly. Supplement with small amounts of phosphorus and potassium fertilizer at other times, but avoid over-fertilizing.",
                        pruningText = "Do not prune strawberries excessively, as this can prevent fruit production. However, old and diseased leaves should be pruned promptly.",
                        pestAndDiseasePreventionText = "Common strawberry diseases include white mold and spider mites. Ensure good ventilation on the balcony to effectively prevent pests. If problems are found, spray the leaves with soapy water or wipe them with alcohol.",
                        lightAndTemperatureText = "Strawberries require plenty of light, at least 8 hours of sunlight daily during the fruiting period. Strawberries flower easily when the temperature is between 10-20°C.",
                        soilRequirementsText = "A good mix is potting soil + coconut coir + perlite in a 3:1:1 ratio for good aeration and to prevent root suffocation."
                    )
                    3 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top3,
                        plantName = "Blueberry",
                        wateringText = "Do not overwater blueberries. Water only when the surface soil is dry. Water thoroughly, avoiding only watering halfway.",
                        fertilizingText = "Blueberries should not be over-fertilized; apply small amounts frequently. During the blueberry flowering period, use potassium dihydrogen phosphate (1:1500) to water the roots to help with fruiting. During the fruiting period, use a high-potassium water-soluble fertilizer (1:1200) once a week to ensure fruit flavor.",
                        pruningText = "After flowering, remove weak branches directly, retaining strong branches. In August, thin out overly dense branches to promote fruiting. In winter, prune old branches, retaining 3-5 healthy new branches at the base.",
                        pestAndDiseasePreventionText = "When blueberry leaves show creases, wrinkles, and yellowing, it indicates that the blueberries have been infected with pests or diseases, and timely removal is necessary. Maintaining good ventilation on the balcony is an effective way to prevent pests and diseases.",
                        lightAndTemperatureText = "Blueberries need 6-8 hours of direct sunlight daily. In summer, provide appropriate shade when temperatures exceed 35°C; in winter, it can overwinter outdoors when temperatures remain above -15°C.",
                        soilRequirementsText = "Blueberries thrive in acidic soil with a pH of 4.0-5.5. A suitable soil mix can be made with leaf mold, pine needles, coarse coconut husks, and sulfur powder (4:2:2:1)."
                    )
                    4 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top4,
                        plantName = "Pomegranate",
                        wateringText = "Do not overwater pomegranates; water only when the surface soil is dry.",
                        fertilizingText = "During the growing season, apply a diluted liquid fertilizer every 10-15 days. Before flowering, apply more phosphorus and potassium fertilizer to promote blooming.",
                        pruningText = "Pomegranate branches are dense and can be pruned into desired shapes, but avoid pruning thick branches. Remove dead and diseased branches promptly to conserve nutrients.",
                        pestAndDiseasePreventionText = "Prevention: Pomegranates are susceptible to powdery mildew. If discovered, immediately isolate the affected plant and spray the leaves, undersides of leaves, and branches with a 1:3 mixture of milk and water. Maintain a dry environment.",
                        lightAndTemperatureText = "Pomegranates are sensitive to cold; the optimal temperature is 18-30°C, and the temperature should not fall below 8°C in winter. It needs 6 hours of direct sunlight daily, but can tolerate weak direct sunlight in winter.",
                        soilRequirementsText = "The larger the pot, the better for pomegranates. A suitable soil mix can be made of compost, garden soil, and river sand in a 3:2:1 ratio, with appropriate addition of organic fertilizer."
                    )
                    else -> getDefaultPlantDetail()
                }
            }
            2 -> { // Balcony vegetables
                when (itemNumber) {
                    1 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top5,
                        plantName = "Basil",
                        wateringText = "Water only when the surface soil is dry. During germination, use a spray bottle to keep the soil surface slightly moist. Reduce watering frequency in winter.",
                        fertilizingText = "Starting one month after germination, apply diluted liquid organic fertilizer every two weeks. During the growing season, switch to nitrogen fertilizer to promote leaf growth.",
                        pruningText = "When the plant reaches 10-15cm in height, pinch off the terminal bud to promote lateral branching. Regularly prune old and yellow leaves.",
                        pestAndDiseasePreventionText = "Maintain ventilation and sufficient light. If aphids are found, spray the leaves with soapy water and isolate infected plants.",
                        lightAndTemperatureText = "Basil needs 6-8 hours of direct sunlight daily. Insufficient light will cause it to grow leggy and have a weak aroma. In the high temperatures of summer, provide shade using shade netting to prevent sunburn on the leaves.",
                        soilRequirementsText = "Choose loose, well-drained, slightly acidic soil, add a small amount of organic fertilizer, and place small stones or clay pebbles at the bottom of the pot to prevent waterlogging and root rot."
                    )
                    2 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top6,
                        plantName = "Lettuce",
                        wateringText = "Lettuce prefers a moist environment and generally needs watering every 2-3 days. Use a spray bottle to mist the soil, ensuring even distribution of moisture.",
                        fertilizingText = "Fertilize lettuce with a diluted liquid fertilizer, pouring it along the edge of the pot. Fertilize every 15 days.",
                        pruningText = "When the lettuce reaches 15-20 cm in height, it's ready to harvest. Pick the mature leaves from the outside in; the smaller leaves inside will continue to grow.",
                        pestAndDiseasePreventionText = "Common pests and diseases affecting vegetables include aphids, spider mites, and cabbage worms. Regularly check the leaves, especially the undersides. If pests are found, spray with a 1:100 mixture of chili water and dish soap, or wipe the leaves with alcohol.",
                        lightAndTemperatureText = "Lettuce is not sensitive to temperature but requires ample sunlight. Place the lettuce in a location on a balcony with indirect sunlight, avoiding prolonged direct sunlight.",
                        soilRequirementsText = "Lettuce is not demanding in terms of soil quality; well-draining soil is sufficient. Adding organic fertilizer as needed is beneficial."
                    )
                    3 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top7,
                        plantName = "Coriander",
                        wateringText = "Cilantro prefers a moist environment and generally needs watering every 2-3 days. You can test this by inserting your finger 2-3 cm into the soil; water when the soil feels dry, and water thoroughly.",
                        fertilizingText = "Apply a diluted liquid fertilizer to cilantro every 1-2 weeks, being careful not to let the fertilizer come into contact with the leaves.",
                        pruningText = "When the cilantro reaches 15-20 cm in height, it's ready for harvest. You can harvest the entire plant, leaving about 1 cm to allow it to continue growing, or you can remove mature leaves, leaving the central part of the plant.",
                        pestAndDiseasePreventionText = "Common pests and diseases affecting vegetables include aphids, spider mites, and cabbage caterpillars. Regularly check the leaves, especially the undersides. If pests are found, spray with a 1:100 solution of chili water and dish soap, or wipe the leaves with alcohol.",
                        lightAndTemperatureText = "Coriander prefers light but cannot tolerate direct sunlight. Place the plant in a south-facing location with indirect light. In summer, when sunlight is strong, provide appropriate shade to prevent the leaves from yellowing.",
                        soilRequirementsText = "Cilantro has shallow roots, so it requires loose, well-drained soil. A mixture of garden soil, leaf mold, and river sand in a 3:2:1 ratio can be used, with appropriate additions of organic fertilizer to ensure soil fertility."
                    )
                    4 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top8,
                        plantName = "Chives",
                        wateringText = "Chives prefer moist conditions, watering every 2-3 days. In summer, when it's hot and water evaporates quickly, water twice a day, morning and evening. In winter, reduce watering to once a week.",
                        fertilizingText = "Chives grow quickly and require a lot of fertilizer. Apply a diluted liquid fertilizer every 1-2 weeks. Two to three days after harvesting, fertilize again to promote rapid growth.",
                        pruningText = "When the chives reach 15-20 cm in height, they can be harvested. Cut them 2-3 cm from the base with scissors; do not cut too low, otherwise it will affect regeneration.",
                        pestAndDiseasePreventionText = "Common pests and diseases affecting vegetables include aphids, spider mites, and cabbage caterpillars. Regularly check the leaves, especially the undersides. If pests are found, spray with a 1:100 mixture of chili water and dish soap, or wipe the leaves with alcohol.",
                        lightAndTemperatureText = "Chives don't require high temperatures, but they need plenty of sunlight. Place them on a balcony where they can receive indirect sunlight; avoid prolonged direct sunlight.",
                        soilRequirementsText = "Chives prefer fertile, loose, and well-drained soil. You can mix leaf mold, garden soil, and river sand in a 3:2:1 ratio, and add organic fertilizer as needed."
                    )
                    else -> getDefaultPlantDetail()
                }
            }
            3 -> { // Air-purifying plants
                when (itemNumber) {
                    1 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top9,
                        plantName = "Monstera deliciosa",
                        wateringText = "Water thoroughly only when the top 2cm of soil is completely dry. Extend watering intervals in autumn and winter. Monstera deliciosa prefers humidity; increase humidity using a humidifier, misting, or by placing a water tray, but avoid waterlogging on the leaves.",
                        fertilizingText = "From April to October, apply diluted liquid fertilizer every two weeks, supplemented with slow-release fertilizer every three months. Stop fertilizing in winter.",
                        pruningText = "In spring, prune old, diseased, and overly dense branches. If aerial roots are too long, prune them appropriately or guide them into the soil. Pruning helps control the plant's shape and promotes new leaf growth.",
                        pestAndDiseasePreventionText = "If yellow leaves appear, first check watering. If leaf edges are dry, increase humidity. If leaves haven't fully unfolded, provide additional light. Treat root rot immediately; trim the rotten parts and repot in fresh soil. Regularly check the undersides of leaves.",
                        lightAndTemperatureText = "Maintain a growing temperature of 18-28°C; do not allow temperatures to drop below 10°C in winter. If there is insufficient light, new leaves will become smaller and the stems will elongate; if there is too much light, the leaves will turn yellow and develop sunspots.",
                        soilRequirementsText = "Choose loose, well-draining soil."
                    )
                    2 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top10,
                        plantName = "Pothos",
                        wateringText = "Pothos prefers moist conditions, but the pot should not be waterlogged. Water only when the top 2cm of soil is dry, keeping the soil slightly moist.",
                        fertilizingText = "Use organic fertilizer or slow-release fertilizer regularly. During the peak growing season (spring and summer), fertilize monthly. Reduce fertilization in autumn and stop fertilizing in winter.",
                        pruningText = "Regularly prune overly long, dense, or yellowing branches and leaves to maintain the plant's appearance and promote new leaf growth.",
                        pestAndDiseasePreventionText = "Maintain good air humidity; otherwise, leaf tips will turn yellow. If entire leaves turn yellow, check for overwatering and replace the soil, ensuring good ventilation. If the plant is infected with pests or diseases, use physical control methods (such as sticky traps) and isolate infected plants.",
                        lightAndTemperatureText = "Pothos prefers warmth, 18-28°C. Temperatures below 10°C can cause cold damage. It is highly shade-tolerant and should not be exposed to direct sunlight. It is best placed in a bright location with diffused light.",
                        soilRequirementsText = "Pothos prefers loose, fertile, well-drained, slightly acidic to neutral soil. Adding organic fertilizer as needed is beneficial."
                    )
                    3 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top11,
                        plantName = "Aloe vera",
                        wateringText = "Aloe vera is a desert plant and should not be overwatered. You can test the soil by inserting your finger 3-5 cm into the soil; water only when the soil feels completely dry. Ensure water drains from the bottom of the pot. Water once a month during winter.",
                        fertilizingText = "During the spring and autumn growing seasons, apply a compound fertilizer monthly. Otherwise, fertilization is not necessary.",
                        pruningText = "Regularly prune the roots and leaves to maintain the plant's appearance and promote new leaf growth.",
                        pestAndDiseasePreventionText = "Aloe vera is susceptible to spider mites. Ensure good ventilation on your balcony. If problems are found, spray the leaves with soapy water or wipe them with alcohol, and isolate infected plants.",
                        lightAndTemperatureText = "Aloe vera needs ample indirect light. Insufficient light will cause the leaves to become thin, etiolated, and limp. In winter, keep the plant warm; the temperature should not drop below 5°C.",
                        soilRequirementsText = "Aloe vera requires loose, well-draining sandy soil. A good mix is potting soil with plenty of perlite and river sand to ensure good drainage and prevent waterlogging and root rot."
                    )
                    4 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top12,
                        plantName = "Chlorophytum comosum",
                        wateringText = "Water every 3 days in spring and summer, and every 7 days in autumn and winter. You can test the soil by inserting your finger 2-3 cm into the soil; if it feels completely dry, water only. Ensure water drains from the bottom of the pot.",
                        fertilizingText = "Apply liquid fertilizer monthly, ensuring it is double-diluted. Stop fertilizing when the temperature is below 15°C or above 35°C.",
                        pruningText = "Regularly prune yellowed and blackened branches and leaves, preserving new shoots and roots.",
                        pestAndDiseasePreventionText = "The plant is susceptible to spider mites. Ensure good ventilation on the balcony. If problems are found, spray the leaves with soapy water or wipe them with alcohol, and isolate infected plants.",
                        lightAndTemperatureText = "Chlorophytum comosum is shade-tolerant, but it grows better in full sun. Avoid direct midday sun in summer and provide warmth in winter.",
                        soilRequirementsText = "Use loose, fertile, well-draining potting mix with perlite. Adding organic fertilizer is also beneficial."
                    )
                    else -> getDefaultPlantDetail()
                }
            }
            4 -> { // Ornamental plants
                when (itemNumber) {
                    1 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top13,
                        plantName = "Malabar chestnut",
                        wateringText = "Water only when the soil is completely dry. During the germination period, use a spray bottle to keep the soil surface slightly moist. Reduce watering frequency in winter. Do not spray water on the leaves.",
                        fertilizingText = "During the growing season, apply a diluted water-soluble compound fertilizer to the roots every 15 days to provide a balanced supply of nitrogen, phosphorus, and potassium.",
                        pruningText = "Prune overly long branches in spring to maintain an attractive umbrella shape.",
                        pestAndDiseasePreventionText = "When the plant becomes overly long, the leaves become sparse, and yellow and fall off, increase light exposure and reduce watering frequency. If the leaf tips are dry and curled, rinse the surface of the potting soil with clean water to remove residual fertilizer and prune any dry leaves.",
                        lightAndTemperatureText = "Japanese umbrella plants prefer partial shade with diffused light, requiring 4-6 hours of direct sunlight daily. Avoid direct sunlight at midday. The optimal growing temperature is 18-28°C. In winter, prevent temperatures from falling below 10°C to avoid frost damage. In summer, increase ventilation if temperatures exceed 30°C.",
                        soilRequirementsText = "Use a mixture of leaf mold, coarse sand, and organic fertilizer to ensure good soil aeration."
                    )
                    2 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top14,
                        plantName = "Gardenia",
                        wateringText = "Water only when the top 2-3cm of soil is completely dry. Reduce watering frequency in winter. Keep the soil moist.",
                        fertilizingText = "Gardenias are not very tolerant of alkaline soil. Apply a diluted alum fertilizer solution 1-2 times per month. During the flowering period, potassium dihydrogen phosphate can be used to promote blooming.",
                        pruningText = "Prune dense inner branches and weak lower branches to improve air circulation and light penetration. When there are many flower buds, prune some, being careful to retain outward-growing buds.",
                        pestAndDiseasePreventionText = "The plant is susceptible to spider mites. Ensure good ventilation on the balcony. If problems are found, spray the leaves with soapy water or wipe them with alcohol, and isolate infected plants.",
                        lightAndTemperatureText = "Gardenias prefer light but dislike direct sunlight. Provide full sun in spring and autumn, and provide shade in summer.",
                        soilRequirementsText = "Choose loose, well-draining, acidic soil. You can add appropriate amounts of organic fertilizer. Choose well-ventilated terracotta or purple clay pots."
                    )
                    3 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top15,
                        plantName = "Jasminum",
                        wateringText = "Jasmine prefers moist conditions. In summer, water thoroughly in the morning and evening. At other times, you can test the soil by inserting your finger 2-3 cm into the soil; if it feels completely dry, water again. Ensure water drains from the bottom of the pot.",
                        fertilizingText = "During the flowering season, you can add organic fertilizer appropriately, such as well-rotted sheep manure or potassium dihydrogen phosphate. At other times, pay attention to moisture retention and sunlight; fertilization is not necessary.",
                        pruningText = "Jasmine is very tolerant of pruning. After flowering, if there are green buds on the branches, prune above them to encourage further sprouting and flowering. Leave the branches for winter without pruning.",
                        pestAndDiseasePreventionText = "Maintain good ventilation. If the plant is found to be infected with pests or diseases, choose physical control methods (such as using sticky traps to catch insects) and isolate infected plants.",
                        lightAndTemperatureText = "Jasmine requires year-round sunlight. Ample sunlight will help plants bloom. In winter, keep them warm and ensure the temperature does not drop below 5°C.",
                        soilRequirementsText = "Jasmine prefers slightly acidic soil. You can spread clean pine needles on the soil surface. A suitable soil mix can be made of native soil, peat moss, coconut husks, coconut coir, and rice husks in a ratio of approximately 4:3:1:1:1."
                    )
                    4 -> PlantDetailEntity(
                        topImageResource = R.drawable.start22_top16,
                        plantName = "Crassulaceae",
                        wateringText = "Crassulaceae is drought-tolerant and should not be overwatered. During the spring and autumn growing seasons, water every 10 days; during the autumn and winter dormancy season, water every 20 days. You can test for dryness by inserting your finger 2-3 cm into the soil; water only when the soil feels completely dry.",
                        fertilizingText = "Slow-release fertilizer can be applied to the soil surface every 3 months. Stop fertilizing when the temperature is below 15°C or above 35°C.",
                        pruningText = "Prune excessively long or withered branches, allowing the cut ends to dry before watering. Pinching off the terminal buds promotes lateral bud growth and a fuller plant shape.",
                        pestAndDiseasePreventionText = "Crassulaceae is susceptible to aphids and scale insects. If found, wipe the leaves with alcohol every 7 days. Black rot is also a common disease; if discovered, immediately cut off the blackened stems, leaving healthy leaves. Apply wood ash to the wounds and allow them to dry for 3 days before propagation by cuttings.",
                        lightAndTemperatureText = "Crassulaceae needs plenty of sunlight. Insufficient light will cause the leaves to become thin, elongated, and limp. In winter, it is important to keep them warm; the temperature should not drop below 5°C.",
                        soilRequirementsText = "Use loose, fertile, well-draining potting mix with perlite. Adding organic fertilizer as needed is also beneficial."
                    )
                    else -> getDefaultPlantDetail()
                }
            }
            else -> getDefaultPlantDetail()
        }
    }

    private fun getDefaultPlantDetail(): PlantDetailEntity {
        return PlantDetailEntity(
            topImageResource = R.drawable.bg_start_21_item1,
            plantName = "Default Plant",
            wateringText = "Default watering instructions",
            fertilizingText = "Default fertilizing instructions",
            pruningText = "Default pruning instructions",
            pestAndDiseasePreventionText = "Default pest prevention",
            lightAndTemperatureText = "Default light requirements",
            soilRequirementsText = "Default soil requirements"
        )
    }

    private fun selectButton(buttonNumber: Int) {
        if (currentSelectedButton == buttonNumber) return

        // Reset all buttons to unselected state
        resetAllButtons()

        // Set selected button
        when (buttonNumber) {
            1 -> {
                button1.setBackgroundResource(R.drawable.bg_start_21_selected)
                button1.setTextColor(resources.getColor(android.R.color.white, null))
            }
            2 -> {
                button2.setBackgroundResource(R.drawable.bg_start_21_selected)
                button2.setTextColor(resources.getColor(android.R.color.white, null))
            }
            3 -> {
                button3.setBackgroundResource(R.drawable.bg_start_21_selected)
                button3.setTextColor(resources.getColor(android.R.color.white, null))
            }
            4 -> {
                button4.setBackgroundResource(R.drawable.bg_start_21_selected)
                button4.setTextColor(resources.getColor(android.R.color.white, null))
            }
        }

        currentSelectedButton = buttonNumber
    }

    private fun resetAllButtons() {
        val unselectedColor = resources.getColor(android.R.color.darker_gray, null)
        
        button1.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button1.setTextColor(unselectedColor)
        
        button2.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button2.setTextColor(unselectedColor)
        
        button3.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button3.setTextColor(unselectedColor)
        
        button4.setBackgroundResource(R.drawable.bg_start_21_unselected)
        button4.setTextColor(unselectedColor)
    }

    private fun updateItems(buttonNumber: Int) {
        when (buttonNumber) {
            1 -> {
                // Balcony fruits
                item1.setImageResource(R.drawable.bg_start_21_item1)
                item2.setImageResource(R.drawable.bg_start_21_item2)
                item3.setImageResource(R.drawable.bg_start_21_item3)
                item4.setImageResource(R.drawable.bg_start_21_item4)
            }
            2 -> {
                // Balcony vegetables
                item1.setImageResource(R.drawable.bg_start_21_item5)
                item2.setImageResource(R.drawable.bg_start_21_item6)
                item3.setImageResource(R.drawable.bg_start_21_item7)
                item4.setImageResource(R.drawable.bg_start_21_item8)
            }
            3 -> {
                // Air-purifying plants
                item1.setImageResource(R.drawable.bg_start_21_item9)
                item2.setImageResource(R.drawable.bg_start_21_item10)
                item3.setImageResource(R.drawable.bg_start_21_item11)
                item4.setImageResource(R.drawable.bg_start_21_item12)
            }
            4 -> {
                // Ornamental plants
                item1.setImageResource(R.drawable.bg_start_21_item13)
                item2.setImageResource(R.drawable.bg_start_21_item14)
                item3.setImageResource(R.drawable.bg_start_21_item15)
                item4.setImageResource(R.drawable.bg_start_21_item16)
            }
        }
    }
}
