<script>
  import { onMount } from 'svelte';

  let products = [];
  let error = null;
  const catalogUrl = 'http://localhost:8080/saas/catalog';
  const apiKey = "Uditp_11";

  async function fetchProducts() {
    try {
      const response = await fetch(catalogUrl, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'X-API-KEY': apiKey
        }
      });
      if (!response.ok) {
        throw new Error(`Error fetching products: ${response.statusText}`);
      }
      products = await response.json();
    } catch (err) {
      error = err.message;
    }
  }

  onMount(() => {
    fetchProducts();
  });
</script>

<main>
  <h1>Product Catalog</h1>
  {#if error}
    <p class="error">{error}</p>
  {:else if products.length === 0}
    <p>Loading products...</p>
  {:else}
    <div class="table-container">
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Price</th>
            <th>Size</th>
            <th>Color</th>
          </tr>
        </thead>
        <tbody>
          {#each products as product (product.id)}
            <tr>
              <td>{product.id}</td>
              <td>{product.name}</td>
              <td>{product.type}</td>
              <td>{product.price}</td>
              <td>{product.size}</td>
              <td>{product.color}</td>
            </tr>
          {/each}
        </tbody>
      </table>
    </div>
  {/if}
</main>

<style>
  main {
    padding: 50px;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  }

  .error {
    color: red;
  }

  /* Container to center the table */
  .table-container {
    max-width: 800px;
    margin: 40px auto;
    padding: 0 20px;
  }

  table {
    width: 100%;
    border-collapse: collapse;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  }

  thead {
    background-color: #4CAF50;
    color: #ffffff;
  }

  th, td {
    padding: 12px 15px;
    text-align: left;
  }

  tbody tr {
    border-bottom: 1px solid #dddddd;
  }

  tbody tr:nth-of-type(odd) {
    background-color: #FF8A65;
  }

  tbody tr:nth-of-type(even) {
    background-color: #FF7043;
  }

  tbody tr:hover {
    background-color: #F4511E;
  }

  tbody tr:last-of-type {
    border-bottom: 2px solid #4CAF50;
  }
</style>
