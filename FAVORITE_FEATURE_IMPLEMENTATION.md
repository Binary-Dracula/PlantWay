# 收藏功能实现说明

## 功能概述

实现了从 Community3Activity 点击收藏按钮将商品添加到收藏列表，并在 Profile2Activity 中使用 RecyclerView 展示收藏的商品，支持长按删除。

## 实现内容

### 1. CommunityDataManager 收藏功能

新增收藏相关方法：

```kotlin
// 收藏列表
private val favoriteList = mutableListOf<CommunityItem>()
private val favoriteListeners = mutableListOf<() -> Unit>()

// 获取收藏列表
fun getFavoriteList(): List<CommunityItem>

// 添加到收藏
fun addToFavorite(item: CommunityItem)

// 从收藏移除
fun removeFromFavorite(id: Int)

// 检查是否已收藏
fun isFavorite(id: Int): Boolean

// 收藏变化监听器
fun addFavoriteChangeListener(listener: () -> Unit)
fun removeFavoriteChangeListener(listener: () -> Unit)
```

**特点：**
- 独立的收藏列表管理
- 独立的数据变化监听器
- 自动去重（同一商品不会重复收藏）
- 数据变化自动通知所有监听者

### 2. Community3Activity 收藏按钮

更新收藏按钮点击逻辑：

```kotlin
findViewById<LinearLayout>(R.id.ll_favorite).setOnClickListener {
    currentItem?.let { item ->
        if (CommunityDataManager.isFavorite(item.id)) {
            // 已收藏，取消收藏
            CommunityDataManager.removeFromFavorite(item.id)
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
        } else {
            // 未收藏，添加收藏
            CommunityDataManager.addToFavorite(item)
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
        }
    }
}
```

**功能：**
- 点击收藏按钮添加到收藏列表
- 如果已收藏，再次点击取消收藏
- 显示相应的 Toast 提示

### 3. FavoriteAdapter

创建专门的收藏列表适配器：

**特点：**
- 支持显示用户拍摄的照片和默认图片
- 支持点击跳转到详情页
- 支持长按删除
- 布局与 Profile2Activity 原有样式一致

**布局文件：** `item_favorite.xml`
- 140dp x 140dp 圆角产品图片
- 产品标题（18sp，粗体）
- 卖家信息（头像 + 名称）
- 价格显示（24sp，粗体）

### 4. Profile2Activity 更新

完全重构为 RecyclerView 展示：

```kotlin
class Profile2Activity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteAdapter
    private lateinit var deleteConfirmationHelper: DeleteConfirmationHelper
    
    // 功能：
    // 1. 显示收藏列表
    // 2. 点击item跳转到详情页
    // 3. 长按删除（使用DeleteConfirmationHelper）
    // 4. 监听收藏数据变化，自动更新
}
```

**布局更新：**
- 移除原有的单个 Product Card
- 添加 RecyclerView (`rv_favorites`)
- 使用 LinearLayoutManager 垂直列表布局

### 5. 删除功能

使用 DeleteConfirmationHelper 实现：

```kotlin
private fun showDeleteConfirmation(item: CommunityItem) {
    deleteConfirmationHelper.show(
        title = "Remove from Favorites",
        message = "Are you sure you want to remove ${item.title} from favorites?",
        onConfirm = {
            CommunityDataManager.removeFromFavorite(item.id)
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
        }
    )
}
```

**特点：**
- 长按 item 触发删除确认
- 显示自定义确认对话框
- 确认后从收藏列表移除
- 自动更新界面

## 数据流程

### 添加收藏流程
```
1. 用户在 Community3Activity 查看商品详情
   ↓
2. 点击收藏按钮 (ll_favorite)
   ↓
3. CommunityDataManager.addToFavorite(item)
   ↓
4. 触发 favoriteListeners 通知
   ↓
5. Profile2Activity 自动更新显示
```

### 删除收藏流程
```
1. 用户在 Profile2Activity 长按收藏的商品
   ↓
2. 显示 DeleteConfirmationHelper 对话框
   ↓
3. 用户确认删除
   ↓
4. CommunityDataManager.removeFromFavorite(id)
   ↓
5. 触发 favoriteListeners 通知
   ↓
6. Profile2Activity 自动更新显示
```

### 查看详情流程
```
1. 用户在 Profile2Activity 点击收藏的商品
   ↓
2. 跳转到 Community3Activity
   ↓
3. 传递 item.id
   ↓
4. 显示商品详细信息
```

## 文件清单

### 新建文件
1. `FavoriteAdapter.kt` - 收藏列表适配器
2. `item_favorite.xml` - 收藏列表 item 布局

### 修改文件
1. `CommunityDataManager.kt` - 添加收藏功能
2. `Community3Activity.kt` - 更新收藏按钮逻辑
3. `Profile2Activity.kt` - 改用 RecyclerView 显示收藏列表
4. `activity_profile2.xml` - 更新布局为 RecyclerView

## 功能特点

### 1. 数据同步
- 使用监听器模式实现数据自动同步
- Profile2Activity 注册监听器，收藏数据变化时自动更新
- 生命周期管理：onDestroy 时移除监听器

### 2. 去重机制
```kotlin
fun addToFavorite(item: CommunityItem) {
    if (!favoriteList.any { it.id == item.id }) {
        favoriteList.add(item)
        notifyFavoriteChanged()
    }
}
```

### 3. 图片加载
- 优先显示用户拍摄的照片
- 回退到默认资源图片
- 完整的错误处理

### 4. 用户体验
- 点击收藏按钮有 Toast 提示
- 长按删除有确认对话框
- 删除后有成功提示
- 界面自动更新，无需手动刷新

## 测试场景

### 场景 1：添加收藏
1. 打开 CommunityFragment，点击任意商品
2. 在 Community3Activity 点击收藏按钮
3. 看到 "Added to favorites" 提示
4. 返回主页，进入 Profile2Activity
5. 应该看到刚收藏的商品

### 场景 2：查看收藏详情
1. 在 Profile2Activity 点击收藏的商品
2. 跳转到 Community3Activity
3. 显示完整的商品信息

### 场景 3：删除收藏
1. 在 Profile2Activity 长按收藏的商品
2. 显示删除确认对话框
3. 点击确认
4. 商品从列表中移除
5. 看到 "Removed from favorites" 提示

### 场景 4：重复收藏
1. 收藏一个商品
2. 再次点击收藏按钮
3. 应该取消收藏（从 Profile2Activity 移除）

## 注意事项

1. 收藏数据存储在内存中，应用重启后会丢失
2. 如需持久化，可以使用 SharedPreferences 或数据库
3. 收藏列表和社区列表是独立的
4. 删除社区商品不会自动从收藏列表移除（可根据需求调整）
5. 监听器需要在 Activity/Fragment 销毁时移除，避免内存泄漏
