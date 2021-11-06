<script>
	import ChartWrap from "./components/ChartWrap.svelte";
	const data = {
		labels: ["1", "2", "3", "4"],
		datasets: [
			{
				label: "ETH",
				data: [1, 2, 3, 4],
			},
		],
	};
</script>

<main>
	<h1>ETH_Watch</h1>
	<article>
		<section>
			<ChartWrap {data} />
		</section>
	</article>
</main>

<style>
	main {
		text-align: center;
		padding: 1em;
		max-width: 240px;
		margin: 0 auto;
	}

	h1 {
		color: #ff3e00;
		text-transform: uppercase;
		font-size: 4em;
		font-weight: 100;
	}

	@media (min-width: 640px) {
		main {
			max-width: none;
		}
	}
</style>
