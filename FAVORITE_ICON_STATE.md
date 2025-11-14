# 收藏图标状态更新说明

## 功能概述

实现了 Community3Activity 中 `iv_favorite` 图标根据收藏状态动态切换显示。

## 实现内容

### 1. 新增属性

```kotlin
private lateinit var ivFavorite: ImageView
```

在 `setupViews()` 中初始化：
```kotlin
ivFavorite = findViewById(R.id.iv_favorite)
```

### 2. 更新收藏图标方法

```kotlin
private fun updateFavoriteIcon() {
    currentItem?.let { item ->
        if (CommunityDataManager.isFavorite(item.id)) {
            // 已收藏，显示实心图标
            ivFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            // 未收藏，显示空心图标
            ivFavorite.setImageResource(R.drawable.ic_unfavorite)
        }
    }
}
```

### 3. 图标状态

| 状态 | 图标 | 说明 |
|------|------|------|
| 未收藏 | `ic_unfavorite.png` | 空心图标 |
| 已收藏 | `ic_favorite.png` | 实心图标 |

### 4. 更新时机

#### 初始化时
在 `setupViews()` 中调用 `updateFavoriteIcon()`，根据当前商品是否已收藏显示对应图标。

```kotlin
// 初始化收藏状态
updateFavoriteIcon()
```

#### 点击收藏按钮时
点击收藏/取消收藏后立即更新图标。

```kotlin
findViewById<LinearLayout>(R.id.ll_favorite).setOnClickListener {
    currentItem?.let { item ->
        if (CommunityDataManager.isFavorite(item.id)) {
            CommunityDataManager.removeFromFavorite(item.id)
            Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show()
        } else {
            CommunityDataManager.addToFavorite(item)
            Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show()
        }
        updateFavoriteIcon() // 更新图标
    }
}
```

#### Activity 恢复时
在 `onResume()` 中更新图标，确保从其他页面返回时状态正确。

```kotlin
override fun onResume() {
    super.onResume()
    // 每次返回时更新收藏状态
    updateFavoriteIcon()
}
```

## 用户体验流程

### 场景 1：首次查看商品（未收藏）
```
1. 打开 Community3Activity
   ↓
2. 调用 updateFavoriteIcon()
   ↓
3. 检查 isFavorite(item.id) = false
   ↓
4. 显示空心图标 (ic_unfavorite)
```

### 场景 2：点击收藏
```
1. 用户点击收藏按钮
   ↓
2. 检查当前状态：未收藏
   ↓
3. 调用 addToFavorite(item)
   ↓
4. 显示 Toast: "Added to favorites"
   ↓
5. 调用 updateFavoriteIcon()
   ↓
6. 图标切换为实心 (ic_favorite)
```

### 场景 3：点击取消收藏
```
1. 用户点击收藏按钮
   ↓
2. 检查当前状态：已收藏
   ↓
3. 调用 removeFromFavorite(item.id)
   ↓
4. 显示 Toast: "Removed from favorites"
   ↓
5. 调用 updateFavoriteIcon()
   ↓
6. 图标切换为空心 (ic_unfavorite)
```

### 场景 4：从 Profile2Activity 返回
```
1. 用户在 Profile2Activity 删除收藏
   ↓
2. 返回 Community3Activity
   ↓
3. onResume() 被调用
   ↓
4. 调用 updateFavoriteIcon()
   ↓
5. 图标更新为空心 (ic_unfavorite)
```

## 代码变更总结

### 新增
- `ivFavorite: ImageView` 属性
- `updateFavoriteIcon()` 方法
- `onResume()` 生命周期方法

### 修改
- `setupViews()`: 初始化 ivFavorite 并调用 updateFavoriteIcon()
- 收藏按钮点击事件: 添加 updateFavoriteIcon() 调用

### 移除
- `isFavorited` 变量（不再需要，直接从 CommunityDataManager 查询）
- `favoriteCount` 相关逻辑（使用实际的收藏状态）

## 优势

1. **实时同步**：图标状态始终与实际收藏状态一致
2. **自动更新**：从其他页面返回时自动刷新状态
3. **视觉反馈**：用户可以清楚地看到当前收藏状态
4. **单一数据源**：状态由 CommunityDataManager 统一管理

## 测试要点

1. ✅ 首次打开未收藏的商品，显示空心图标
2. ✅ 首次打开已收藏的商品，显示实心图标
3. ✅ 点击收藏，图标从空心变为实心
4. ✅ 点击取消收藏，图标从实心变为空心
5. ✅ 在 Profile2Activity 删除收藏后返回，图标更新为空心
6. ✅ 多次点击收藏按钮，图标正确切换
