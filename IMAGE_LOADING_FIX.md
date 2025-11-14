# 图片加载修复说明

## 问题描述
CommunityFragment 在显示用户添加的数据时，itemImage 没有显示用户拍摄的照片。

## 问题原因
1. **错误的路径获取**：在 `takePicture` 回调中使用了 `photoUri?.path`，这会返回 URI 的路径部分（如 `/external_files/...`），而不是实际的文件系统路径。
2. **ImageView.setImageURI() 的限制**：在某些情况下，`setImageURI()` 可能无法正确加载文件路径。

## 修复方案

### 1. Community2Activity.kt
- 移除了 `capturedImagePath = photoUri?.path` 这行错误的代码
- `capturedImagePath` 已经在 `createImageFile()` 方法中正确设置为文件的绝对路径
- 这样保存的路径格式为：`/data/user/0/com.plant.way/files/community_images/COMMUNITY_20231201_123456.jpg`

### 2. CommunityAdapter.kt 和 ProfileCommunityAdapter.kt
改进了图片加载逻辑：
- 使用 `BitmapFactory.decodeFile()` 直接从文件路径加载 Bitmap
- 添加了异常处理，如果加载失败则显示默认图片
- 使用 `isNullOrEmpty()` 检查路径是否有效
- 使用 `file.absolutePath` 确保路径正确

```kotlin
if (!item.imagePath.isNullOrEmpty()) {
    val file = java.io.File(item.imagePath)
    if (file.exists()) {
        try {
            val bitmap = android.graphics.BitmapFactory.decodeFile(file.absolutePath)
            if (bitmap != null) {
                itemImage.setImageBitmap(bitmap)
            } else {
                itemImage.setImageResource(item.imageResId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            itemImage.setImageResource(item.imageResId)
        }
    } else {
        itemImage.setImageResource(item.imageResId)
    }
} else {
    itemImage.setImageResource(item.imageResId)
}
```

## 测试步骤
1. 打开 Community2Activity
2. 点击图片上传区域拍照
3. 输入标题和价格
4. 点击 OK 保存
5. 返回 CommunityFragment，应该能看到刚拍摄的照片
6. 打开 Profile3Activity，也应该能看到刚拍摄的照片

## 技术细节

### 文件路径示例
- **正确的路径**（createImageFile 生成）：
  ```
  /data/user/0/com.plant.way/files/community_images/COMMUNITY_20231201_123456.jpg
  ```

- **错误的路径**（photoUri.path）：
  ```
  /external_files/community_images/COMMUNITY_20231201_123456.jpg
  ```

### 为什么使用 BitmapFactory
- `ImageView.setImageURI()` 主要用于 content:// 和 file:// URI
- 对于文件系统的绝对路径，使用 `BitmapFactory.decodeFile()` 更可靠
- 可以更好地控制错误处理和内存管理

## 注意事项
1. 图片文件保存在应用私有目录，卸载应用后会被删除
2. 如果需要持久化保存，考虑使用 MediaStore 或外部存储
3. 大图片可能导致内存问题，生产环境建议使用图片加载库（如 Glide、Coil）
4. 当前实现没有图片压缩，可能占用较多存储空间
