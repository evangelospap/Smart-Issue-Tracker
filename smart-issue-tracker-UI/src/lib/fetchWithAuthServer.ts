import { auth } from "@clerk/nextjs/server";

const BACKEND_API =
  process.env.NEXT_PUBLIC_BACKEND_API || "http://localhost:8081";

export async function fetchWithAuthServer<T>(
  path: string,
  options: RequestInit = {}
) {
  // 🔑 Await the auth() promise
  const { sessionId, getToken } = await auth();
  if (!sessionId) {
    throw new Error("Not authenticated");
  }

 // ✅ get token using your template
  const token = await getToken({ template: "Smart-Issue-Tracker" });
  if (!token) {
    throw new Error("Could not retrieve Clerk token");
  }

  const headers = {
    ...options.headers,
    Authorization: `Bearer ${token}`,
    "Content-Type": "application/json",
  };

  const res = await fetch(`${BACKEND_API}${path}`, { ...options, headers });
  if (!res.ok) {
    const text = await res.text();
    throw new Error(`Request failed: ${res.status} - ${text}`);
  }

  return res.json() as Promise<T>;
}
