# Delete Confirmation Layout Usage

## Overview
This project now includes a custom delete confirmation overlay that replaces Android's AlertDialog. The layout provides a full-screen semi-transparent background with a centered confirmation card.

## Design Specifications
- Background: Semi-transparent gray (#80000000)
- Title "Delete Task": Green (#44E099)
- Cancel Button: Green (#44E099)
- Confirm Button: Orange (#FF6229)
- Card Background: White with rounded corners

## Files Created
1. `app/src/main/res/layout/layout_delete_confirmation.xml` - Main layout file
2. `app/src/main/res/drawable/bg_delete_confirmation.xml` - White card background
3. `app/src/main/res/drawable/bg_button_cancel.xml` - Green cancel button
4. `app/src/main/res/drawable/bg_button_confirm.xml` - Orange confirm button
5. `app/src/main/java/com/plant/way/DeleteConfirmationHelper.kt` - Helper class

## Usage Example

### In Schedule7Activity (Already Implemented)

```kotlin
// 1. Declare the helper as a class property
private lateinit var deleteConfirmationHelper: DeleteConfirmationHelper

// 2. Initialize in onCreate()
deleteConfirmationHelper = DeleteConfirmationHelper(findViewById(android.R.id.content))

// 3. Use it to show delete confirmation
deleteConfirmationHelper.show(
    title = "Delete Task",
    message = "Are you sure you want to delete this Pruning task?",
    onConfirm = {
        // Handle delete action
        PlantDataManager.removeTask(currentPlantId, task.id)
        adapter.removeTask(task)
        Toast.makeText(this, "Task deleted successfully", Toast.LENGTH_SHORT).show()
    },
    onCancel = {
        // Optional: Handle cancel action
    }
)
```

### To Use in Other Activities

```kotlin
// In any Activity
class YourActivity : AppCompatActivity() {
    private lateinit var deleteConfirmationHelper: DeleteConfirmationHelper
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.your_layout)
        
        // Initialize helper with root view
        deleteConfirmationHelper = DeleteConfirmationHelper(findViewById(android.R.id.content))
        
        // Use when needed
        someDeleteButton.setOnClickListener {
            deleteConfirmationHelper.show(
                title = "Delete Task",
                message = "Are you sure you want to delete this item?",
                onConfirm = {
                    // Your delete logic here
                }
            )
        }
    }
}
```

## Customization

### Change Text Dynamically
```kotlin
deleteConfirmationHelper.show(
    title = "Delete Plant",
    message = "Are you sure you want to delete ${plantName}?",
    onConfirm = { /* ... */ }
)
```

### Change Colors
Edit the drawable XML files:
- `bg_button_cancel.xml` - Change cancel button color
- `bg_button_confirm.xml` - Change confirm button color
- Modify `layout_delete_confirmation.xml` for title color

## Migration from AlertDialog

**Before:**
```kotlin
AlertDialog.Builder(this)
    .setTitle("Delete Task")
    .setMessage("Are you sure?")
    .setPositiveButton("Confirm") { dialog, _ ->
        // delete logic
        dialog.dismiss()
    }
    .setNegativeButton("Cancel") { dialog, _ ->
        dialog.dismiss()
    }
    .show()
```

**After:**
```kotlin
deleteConfirmationHelper.show(
    title = "Delete Task",
    message = "Are you sure?",
    onConfirm = {
        // delete logic
    }
)
```

## Notes
- The overlay automatically dismisses when clicking outside the card
- No need to manually dismiss - it's handled automatically
- The helper can be reused multiple times in the same activity
