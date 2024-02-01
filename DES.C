#include <stdio.h>
#include <stdint.h>
// Initial Permutation Table
int initial_permutation[] = {
 58, 50, 42, 34, 26, 18, 10, 2,
 60, 52, 44, 36, 28, 20, 12, 4,
 62, 54, 46, 38, 30, 22, 14, 6,
 64, 56, 48, 40, 32, 24, 16, 8,
 57, 49, 41, 33, 25, 17, 9, 1,
 59, 51, 43, 35, 27, 19, 11, 3,
 61, 53, 45, 37, 29, 21, 13, 5,
 63, 55, 47, 39, 31, 23, 15, 7
};
// Final Permutation Table
int final_permutation[] = {
 40, 8, 48, 16, 56, 24, 64, 32,
 39, 7, 47, 15, 55, 23, 63, 31,
 38, 6, 46, 14, 54, 22, 62, 30,
 37, 5, 45, 13, 53, 21, 61, 29,
 36, 4, 44, 12, 52, 20, 60, 28,
 35, 3, 43, 11, 51, 19, 59, 27,
 34, 2, 42, 10, 50, 18, 58, 26,
 33, 1, 41, 9, 49, 17, 57, 25
};
// Key Generation Permutation Table
int key_permutation[] = {
 57, 49, 41, 33, 25, 17, 9, 1,
 58, 50, 42, 34, 26, 18, 10, 2,
 59, 51, 43, 35, 27, 19, 11, 3,
 60, 52, 44, 36, 63, 55, 47, 39,
 31, 23, 15, 7, 62, 54, 46, 38,
 30, 22, 14, 6, 61, 53, 45, 37,
 29, 21, 13, 5, 28, 20, 12, 4
};
// Subkey Permutation Table
int subkey_permutation[] = {
 14, 17, 11, 24, 1, 5, 3, 28,
 15, 6, 21, 10, 23, 19, 12, 4,
 26, 8, 16, 7, 27, 20, 13, 2,
 41, 52, 31, 37, 47, 55, 30, 40,
 51, 45, 33, 48, 44, 49, 39, 56,
 34, 53, 46, 42, 50, 36, 29, 32
};
// S-Box Tables
int sbox[8][4][16] = {
{
{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
},
{
{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
{3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
// ... (S2 to S8)
},
// ... (S3 to S8)
};
// Permutation Function
uint64_t permute(uint64_t data, const int *permutation, int size) {
uint64_t result = 0;
for (int i = 0; i < size; i++) {
result <<= 1;
result |= (data >> (64 - permutation[i])) & 1;
}
return result;
}
// Expansion Permutation Table
int expansion_permutation[] = {
 32, 1, 2, 3, 4, 5, 4, 5,
 6, 7, 8, 9, 8, 9, 10, 11,
 12, 13, 12, 13, 14, 15, 16, 17,
 16, 17, 18, 19, 20, 21, 20, 21,
 22, 23, 24, 25, 24, 25, 26, 27,
 28, 29, 28, 29, 30, 31, 32, 1
};
// Permutation Function for Feistel network
uint32_t feistel_permute(uint32_t data,
  const int * permutation, int size) {
  uint32_t result = 0;
  for (int i = 0; i < size; i++) {
    result <<= 1;
    result |= (data >> (32 - permutation[i])) & 1;
  }
  return result;
}
// Function declarations
uint64_t initialPermutation(uint64_t plaintext);
uint64_t finalPermutation(uint64_t ciphertext);
void generateSubkeys(uint64_t key, uint64_t subkeys[16]);
// Function to perform Feistel network operation
uint32_t feistel(uint32_t data, uint64_t subkey) {
  // Expansion permutation
  uint64_t expanded = feistel_permute(data, expansion_permutation, 32);
  // XOR with subkey
  expanded ^= subkey;
  // S-Box substitution
  uint32_t result = 0;
  for (int i = 0; i < 8; i++) {
    int row = ((expanded >> (42 - 6 * i)) & 0x21) | ((expanded >> (43 - 6 *
      i)) & 1);
    int col = (expanded >> (42 - 6 * i - 1)) & 0xF;
    result |= (sbox[i][row][col] & 0xF) << (28 - 4 * i);
  }
  // Permutation
  return feistel_permute(result, expansion_permutation, 32);
}
// Function to perform the initial permutation
uint64_t initialPermutation(uint64_t plaintext) {
  return permute(plaintext, initial_permutation, 64);
}
// Function to perform the final permutation
uint64_t finalPermutation(uint64_t ciphertext) {
  return permute(ciphertext, final_permutation, 64);
}
// Function to generate 16 subkeys
void generateSubkeys(uint64_t key, uint64_t subkeys[16]) {
  // Initial key permutation
  key = permute(key, key_permutation, 56);
  // Split the key into two halves
  uint32_t left = key >> 28;
  uint32_t right = key & 0x0FFFFFFF;
  for (int i = 0; i < 16; i++) {
    // Perform circular left shift
    left = ((left << 1) & 0x0FFFFFFF) | ((left >> 27) & 1);
    right = ((right << 1) & 0x0FFFFFFF) | ((right >> 27) & 1);
    // Combine the two halves
    uint64_t combined = ((uint64_t) left << 28) | right;
    // Apply subkey permutation
    subkeys[i] = permute(combined, subkey_permutation, 48);
  }
}
// Function to perform DES encryption
uint64_t encrypt(uint64_t plaintext, uint64_t key) {
  // Initial permutation
  uint64_t permuted = initialPermutation(plaintext);
  // Generate 16 subkeys
  uint64_t subkeys[16];
  generateSubkeys(key, subkeys);
  // Initial split into left and right halves
  uint32_t left = permuted >> 32;
  uint32_t right = permuted & 0xFFFFFFFF;
  // 16 rounds of Feistel network
  for (int i = 0; i < 16; i++) {
    uint32_t temp = right;
    right = left ^ feistel(right, subkeys[i]);
    left = temp;
  }
  // Final combination
  return finalPermutation(((uint64_t) right << 32) | left);
}
// Function to perform DES decryption
uint64_t decrypt(uint64_t ciphertext, uint64_t key) {
  // Initial permutation
  uint64_t permuted = initialPermutation(ciphertext);
  // Generate 16 subkeys
  uint64_t subkeys[16];
  generateSubkeys(key, subkeys);
  // Initial split into left and right halves
  uint32_t left = permuted >> 32;
  uint32_t right = permuted & 0xFFFFFFFF;
  // 16 rounds of Feistel network in reverse order
  for (int i = 15; i >= 0; i--) {
    uint32_t temp = left;
    left = right ^ feistel(left, subkeys[i]);
    right = temp;
  }
  // Final combination
  return finalPermutation(((uint64_t) left << 32) | right);
}
int main() {
  uint64_t plaintext = 0x0123456789ABCDEF;
  uint64_t key = 0x133457799BBCDFF1;
  printf("Plaintext: 0x%016lx\n", plaintext);
  printf("Key: 0x%016lx\n", key);
  // Encryption
  uint64_t ciphertext = encrypt(plaintext, key);
  printf("Ciphertext: 0x%016lx\n", ciphertext);
  // Decryption
  uint64_t decrypted = decrypt(ciphertext, key);
  printf("Decrypted: 0x%016lx\n", plaintext);
  return 0;
}