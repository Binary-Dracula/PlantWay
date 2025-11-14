# Community Feature Implementation

## 功能概述

参考 PlantDataManager 实现了 CommunityDataManager 单例，用于管理社区数据。实现了以下功能：

### 1. CommunityDataManager 单例
- 管理所有社区项目数据
- 区分默认数据和用户添加的数据
- 支持数据变化监听器，实现数据同步

### 2. CommunityFragment 使用 RecyclerView
- 使用 GridLayoutManager 以网格形式显示数据（2列）
- 自动监听数据变化并更新显示
- 点击添加按钮跳转到 Community2Activity

### 3. Community2Activity 添加数据
- 用户可以点击图片上传区域拍照
- 自动请求相机权限
- 拍摄的照片保存到应用私有目录
- 用户可以输入标题、价格和描述（使用 EditText）
- 点击 OK 按钮添加新项目到 CommunityDataManager
- 默认 sellerName 为 "Skyler"，sellerAvatar 为 R.drawable.avatar
- 添加的数据会自动同步到 CommunityFragment 显示

### 4. Profile3Activity 显示用户数据
- 只显示用户添加的数据（不显示默认数据）
- 使用 RecyclerView 网格布局显示
- 支持长按删除功能
- 使用 DeleteConfirmationHelper 显示删除确认对话框
- 删除后数据自动同步到 CommunityFragment

## 文件说明

### 新建文件
1. `CommunityDataManager.kt` - 社区数据管理单例
2. `CommunityItem.kt` - 社区项目数据类
3. `CommunityAdapter.kt` - CommunityFragment 的 RecyclerView 适配器
4. `ProfileCommunityAdapter.kt` - Profile3Activity 的 RecyclerView 适配器（支持长按）
5. `item_community.xml` - RecyclerView 项目布局
6. `fragment_community_new.xml` - CommunityFragment 新布局

### 修改文件
1. `CommunityFragment.kt` - 改用 RecyclerView 显示数据
2. `Community2Activity.kt` - 添加数据保存功能
3. `Profile3Activity.kt` - 显示用户数据并支持长按删除
4. `activity_profile3.xml` - 添加 RecyclerView

## 数据流程

1. **添加数据流程**：
   - 用户在 Community2Activity 输入数据
   - 点击 OK 按钮调用 `CommunityDataManager.addItem()`
   - CommunityDataManager 触发数据变化监听器
   - CommunityFragment 和 Profile3Activity 自动更新显示

2. **删除数据流程**：
   - 用户在 Profile3Activity 长按项目
   - 显示 DeleteConfirmationHelper 确认对话框
   - 确认后调用 `CommunityDataManager.removeItem()`
   - CommunityDataManager 触发数据变化监听器
   - CommunityFragment 和 Profile3Activity 自动更新显示

## 使用说明

### CommunityFragment
- 显示所有社区项目（包括默认数据和用户添加的数据）
- 点击右上角 + 按钮添加新项目

### Community2Activity
- 点击图片上传区域拍照（自动请求相机权限）
- 输入标题、价格和描述（EditText 可编辑）
- 点击 OK 按钮保存
- 图片保存到 external-files-path/community_images/

### Profile3Activity
- 只显示用户添加的项目
- 长按项目显示删除确认对话框
- 确认后删除项目
- 点击右上角 + 按钮添加新项目

## 注意事项

1. 所有数据变化都会自动同步到相关界面
2. Profile3Activity 只显示 `isUserAdded = true` 的数据
3. 删除功能使用 DeleteConfirmationHelper，保持 UI 一致性
4. 数据监听器在 Fragment/Activity 销毁时会自动移除，避免内存泄漏
5. 拍照功能需要相机权限，已在 AndroidManifest.xml 中声明
6. 图片使用 FileProvider 共享，配置在 file_paths.xml
7. 用户拍摄的图片保存在应用私有目录 external-files-path/community_images/
8. 适配器会优先显示用户拍摄的图片，如果没有则显示默认图片资源
