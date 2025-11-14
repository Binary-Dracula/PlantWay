# Community3Activity 数据显示实现

## 功能概述

实现了从 CommunityFragment 和 Profile3Activity 点击 item 跳转到 Community3Activity，并显示对应的 CommunityItem 数据。

## 实现内容

### 1. CommunityFragment 数据传递
- 点击 RecyclerView item 时，通过 Intent 传递 item ID
- 使用 `intent.putExtra("ITEM_ID", item.id)` 传递数据

```kotlin
adapter = CommunityAdapter(CommunityDataManager.getCommunityList()) { item ->
    val intent = Intent(requireActivity(), Community3Activity::class.java)
    intent.putExtra("ITEM_ID", item.id)
    startActivity(intent)
}
```

### 2. Profile3Activity 数据传递
- 同样在点击 item 时传递 item ID 到 Community3Activity
- 保持与 CommunityFragment 一致的数据传递方式

```kotlin
onItemClick = { item ->
    val intent = Intent(this, Community3Activity::class.java)
    intent.putExtra("ITEM_ID", item.id)
    startActivity(intent)
}
```

### 3. Community3Activity 数据接收和显示

#### 新增属性
```kotlin
private lateinit var tvSellerName: TextView
private lateinit var tvProductName: TextView
private lateinit var tvPrice: TextView
private lateinit var tvDescription: TextView
private lateinit var ivAvatar: ImageView
private var currentItem: CommunityItem? = null
```

#### 数据加载
```kotlin
private fun loadItemData() {
    val itemId = intent.getIntExtra("ITEM_ID", -1)
    if (itemId != -1) {
        currentItem = CommunityDataManager.getCommunityList().find { it.id == itemId }
    }
}
```

#### 数据显示
- **卖家名称**：显示 `item.sellerName`
- **产品标题**：显示 `item.title`
- **价格**：显示 `$${item.price}`
- **描述**：显示 `item.description`
- **卖家头像**：显示 `item.sellerAvatar`
- **产品图片**：
  - 如果有用户拍摄的图片（`imagePath`），显示该图片
  - 否则显示默认的资源图片（`imageResId`）

### 4. ImagePagerAdapter 更新

支持两种图片类型：
1. **资源 ID**（Int）：使用 `setImageResource()`
2. **文件路径**（String）：使用 `BitmapFactory.decodeFile()`

```kotlin
private class ImagePagerAdapter(
    private val images: List<Any>,
    private val isFilePath: Boolean
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>()
```

## 数据流程

```
用户操作流程：
1. CommunityFragment/Profile3Activity
   ↓ 点击 item
2. 传递 item.id 到 Community3Activity
   ↓ Intent.putExtra("ITEM_ID", item.id)
3. Community3Activity 接收 ID
   ↓ getIntExtra("ITEM_ID", -1)
4. 从 CommunityDataManager 查找对应的 CommunityItem
   ↓ getCommunityList().find { it.id == itemId }
5. 显示 CommunityItem 的所有数据
   - 标题、价格、描述
   - 卖家信息（名称、头像）
   - 产品图片（用户拍摄或默认图片）
```

## 显示的数据字段

| 字段 | 来源 | 显示位置 |
|------|------|----------|
| title | CommunityItem.title | tv_product_name |
| price | CommunityItem.price | tv_price |
| description | CommunityItem.description | tv_description |
| sellerName | CommunityItem.sellerName | tv_seller_name |
| sellerAvatar | CommunityItem.sellerAvatar | iv_avatar |
| imagePath/imageResId | CommunityItem | vp_images (ViewPager) |

## 图片显示逻辑

```kotlin
val images = if (!item.imagePath.isNullOrEmpty()) {
    listOf(item.imagePath)  // 用户拍摄的图片
} else {
    listOf(item.imageResId)  // 默认资源图片
}
vpImages.adapter = ImagePagerAdapter(images, item.imagePath != null)
```

## 容错处理

1. **Item 不存在**：如果找不到对应的 item，显示默认图片
2. **图片加载失败**：捕获异常，显示默认图片
3. **文件不存在**：检查文件是否存在，不存在则显示默认图片

## 测试场景

### 场景 1：查看默认数据
1. 打开 CommunityFragment
2. 点击默认的 item（如 Gardening Kit）
3. 应该显示默认的标题、价格、描述和图片

### 场景 2：查看用户添加的数据
1. 在 Community2Activity 添加新 item（拍照、输入信息）
2. 返回 CommunityFragment，点击新添加的 item
3. 应该显示用户输入的信息和拍摄的照片

### 场景 3：从 Profile3Activity 查看
1. 打开 Profile3Activity
2. 点击用户添加的 item
3. 应该显示完整的 item 信息

## 注意事项

1. 使用 item ID 作为唯一标识符传递数据
2. 支持显示用户拍摄的照片和默认资源图片
3. 所有文本字段都从 CommunityItem 动态加载
4. ViewPager 支持滑动查看多张图片（当前实现为单张）
5. 保持了原有的点赞、收藏和聊天功能
