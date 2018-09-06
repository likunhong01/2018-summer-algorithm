package 常规题目;

public class 两数相加 {
	
	public static int[] twoSum(int[] nums, int target) {
        int[] two = new int[2];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i == j) {
					continue;
				}
				if (nums[i] + nums[j] == target) {
					two[0] = nums[i];
					two[1] = nums[j];
				}
			}
			
		}
		return two;
    }
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
	}

}
